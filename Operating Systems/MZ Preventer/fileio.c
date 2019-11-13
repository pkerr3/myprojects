//////////////////////////////////////////////////////////////////////
// Intentionally flawed system call library that implements          //
// (unfortunately, not) "safe" file I/O, "preventing" writing "MZ"   //
// at the beginning of a file.                                       //
//                                                                   //
// Written by Golden G. Richard III (@nolaforensix), 7/2017          //
//                                                                   //
// Props to Brian Hay for a similar exercise he used in a recent     //
// training.                                                        //
///////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fileio.h"

//
// GLOBALS
//

FSError fserror;

//
// private functions
//

static int seek_file(File file, SeekAnchor start, long offset) {
  if (! file->fp || (start != BEGINNING_OF_FILE && 
	       start != CURRENT_POSITION && start != END_OF_FILE)) {
    return 0;
  }
  else {
    if (! fseek(file->fp, offset, start == BEGINNING_OF_FILE ? SEEK_SET : 
		(start == END_OF_FILE ? SEEK_END : SEEK_CUR))) {
      return 1;
    }
    else {
      return 0;
    }
  }
}

//
// public functions
//

// open or create a file with pathname 'name' and return a File
// handle.  The file is always opened with read/write access. If the
// open operation fails, the global 'fserror' is set to OPEN_FAILED,
// otherwise to NONE.
File open_file(char *name) {
  File f;

  f = malloc(sizeof(FileInternal));
  fserror=NONE;
  // try to open existing file
  f->fp=fopen(name, "r+");
  if (! f->fp) {
    // fail, fall back to creation
    f->fp=fopen(name, "w+");
    if (! f->fp) {
      fserror=OPEN_FAILED;
      return NULL;
    }
  }
  f->buf[0] = 0;
  f->buf[1] = 0;
  read_file_from(f, f->buf, 2, BEGINNING_OF_FILE, 0);

  //fix fileposition!
  seek_file(f,BEGINNING_OF_FILE, 0L);
  return f;
}



// close a 'file'.  If the close operation fails, the global 'fserror'
// is set to CLOSE_FAILED, otherwise to NONE.
void close_file(File file) {
  if (file->fp && ! fclose(file->fp)) {
    fserror=NONE;
  }
  else {
    fserror=CLOSE_FAILED;
  }
  if (file){
    free(file);
  }
}

// read at most 'num_bytes' bytes from 'file' into the buffer 'data',
// starting 'offset' bytes from the 'start' position.  The starting
// position is BEGINNING_OF_FILE, CURRENT_POSITION, or END_OF_FILE. If
// the read operation fails, the global 'fserror' is set to READ_FAILED,
// otherwise to NONE.
unsigned long read_file_from(File file, void *data, unsigned long num_bytes, 
			     SeekAnchor start, long offset) {

  unsigned long bytes_read=0L;
  
  fserror=NONE;
  if (! file->fp || ! seek_file(file, start, offset)) {
    fserror=READ_FAILED;
  }
  else {
    bytes_read=fread(data, 1, num_bytes, file->fp);
    if (ferror(file->fp)) {
      fserror=READ_FAILED;
    }
  }
  return bytes_read;
}
  
// write 'num_bytes' to 'file' from the buffer 'data', starting
// 'offset' bytes from the 'start' position.  The starting position is
// BEGINNING_OF_FILE, CURRENT_POSITION, or END_OF_FILE.  If an attempt
// is made to modify a file such that "MZ" appears in the first two
// bytes of the file, the write operation fails and ILLEGAL_MZ is
// stored in the global 'fserror'.  If the write fails for any other
// reason, the global 'fserror' is set to WRITE_FAILED, otherwise to
// NONE.
unsigned long write_file_at(File file, void *data, unsigned long num_bytes, 
			     SeekAnchor start, long offset) {
  unsigned long bytes_written=0L;
  fserror=NONE;

  
  if (! file->fp || ! seek_file(file, start, offset)) {
    fserror=WRITE_FAILED;
  }
 else if (offset == 0L && ! strncmp(data, "MZ", 2)) {
    // don't let MZ ever appear at the beginning of the file!
    // (it can't be this easy, can it?)
    fserror=ILLEGAL_MZ;
  }
  //checking if M is in the first position of the file and storing M in the buffer
  else if (offset == 0L && ! strncmp((char*) data, "M", 1)){
    file->buf[0] = (char) data;
    //Testing if Z is written to file
    if(! strncmp(&file->buf[0], "M", 1)){
      if (offset == 1L && ! strncmp((char*) data, "Z", 1)){
        fserror = ILLEGAL_MZ;
      }
  }
  }
  //checking if Z is in the second position of the file and storing Z in the buffer
  else if (offset == 1L && ! strncmp((char*) data, "Z", 1)){
    file->buf[1] = (char) data;
      //Testing if M is written to file
    if (! strncmp(&file->buf[1], "Z", 1)){
      if (offset == 0L && ! strncmp((char*) data, "M", 1)){
        fserror = ILLEGAL_MZ;
      }
  }
  }
  else {
    bytes_written=fwrite(data, 1, num_bytes, file->fp);
    if (bytes_written < num_bytes) {
      fserror=WRITE_FAILED;
    }
  }
  return bytes_written;
}


// print a string representation of the error indicated by the global
// 'fserror'.
void fs_print_error(void) {
  printf("FS ERROR: ");
  switch (fserror) {
  case NONE:
    puts("NONE");
    break;
  case OPEN_FAILED:
    puts("OPEN_FAILED");
    break;
  case CLOSE_FAILED:
    puts("CLOSE_FAILED");
    break;
  case READ_FAILED:
    puts("READ_FAILED");
    break;
  case WRITE_FAILED:
    puts("WRITE_FAILED");
    break;
  case ILLEGAL_MZ:
    puts("ILLEGAL_MZ: SHAME ON YOU!");
  break;
  default:
    puts("** UNKNOWN ERROR **");
  }
}