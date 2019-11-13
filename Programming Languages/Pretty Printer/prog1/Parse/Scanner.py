# Scanner -- The lexical analyzer for the Scheme printer and interpreter

import sys
import io
from Tokens import *

class Scanner:
    def __init__(self, i):
        self.In = i
        self.buf = []
        self.ch_buf = None

    def read(self):
        if self.ch_buf == None:
            ch = self.In.read(1)
            return ch

        else:
            ch = self.ch_buf
            self.ch_buf = None
            return ch
        return None

    
    def peek(self):
        if self.ch_buf == None:
            self.ch_buf = self.In.read(1)
            return self.ch_buf
        else:
            return self.ch_buf

    @staticmethod
    def isDigit(ch):
        return ch >= '0' and ch <= '9'

    def getNextToken(self):
        try:
            # It would be more efficient if we'd maintain our own
            # input buffer for a line and read characters out of that
            # buffer, but reading individual characters from the
            # input stream is easier.
            ch = self.read()

            while ch == " " or ch == ';':
                if ch == " ":
                    return self.getNextToken()
                if ch == ";":
                    while ch != '\n':
                        ch = self.read()
                break

            # Return None on EOF
            if ch == "":
                return None
    
            # Special characters
            elif ch == '\'':
                return Token(TokenType.QUOTE)
            elif ch == '(':
                return Token(TokenType.LPAREN)
            elif ch == ')':
                return Token(TokenType.RPAREN)
            elif ch == '.':
                #  We ignore the special identifier `...'.
                return Token(TokenType.DOT)

            # Boolean constants
            elif ch == '#':
                ch = self.read()

                if ch == 't':
                    return Token(TokenType.TRUE)
                elif ch == 'f':
                    return Token(TokenType.FALSE)
                elif ch == "":
                    sys.stderr.write("Unexpected EOF following #\n")
                    return None
                else:
                    sys.stderr.write("Illegal character '" +
                                     chr(ch) + "' following #\n")
                    return self.getNextToken()

            # String constants
            elif ch == '"':
                self.buf = []
                
                # #Had to do this so that the while loop works correctly
                ch = self.read()
                #Using peek() as a while loop condition causes weird issues in the scanner
                while ch != '"':
                    self.buf.append(ch)
                    ch = self.read()
                return StrToken("".join(self.buf))

            # Integer constants
            elif self.isDigit(ch):
                #i = ord(ch) - ord('0')
                self.buf = []
                #scan the number and convert it to an integer
                while self.isDigit(self.peek()):
                    self.buf.append(ch)
                    ch = self.read()
                self.buf.append(ch)
                i = "".join(self.buf)
                # make sure that the character following the integer
                # is not removed from the input stream
                return IntToken(int(i))
    
            # Identifiers
            elif ch >= '!' and ch <= 'z':
                # or ch is some other valid first character
                # for an identifier
                self.buf = []
                #scan an identifier into the buffer variable buf
                
                while ch >= '!' and ch <= 'z':

                    #Prevents PARENs from being seen as a character on an identifier 
                    #instead of being recognized as its own identifier (e.g. INCORRECT --> "var)" | CORRECT --> "var" ")")
                    if ch == '(' or ch == ')':
                        self.ch_buf = ch
                        break

                    self.buf.append(ch)
                    ch = self.read()
                # make sure that the character following the identifier
                # is not removed from the input stream
                self.ch_buf = ch
                return IdentToken("".join(self.buf))

            # Illegal character
            else:
                    #return None
                #sys.stderr.write("Illegal input character '" + ch + "'\n")
                return self.getNextToken()

        except IOError:
            sys.stderr.write("IOError: error reading input file\n")
            return None


if __name__ == "__main__":
    scanner = Scanner(sys.stdin)
    tok = scanner.getNextToken()
    tt = tok.getType()
    print(tt)
    if tt == TokenType.INT:
        print(tok.getIntVal())
