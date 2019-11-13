#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "prioque.h"

typedef struct _Process{
    int arrival_time, required_time, priority, pid;
    int q, ready, behaviors;
} Process;

typedef struct _ProcessBehavior{
    int priority, cpuburst, ioburst;
    int q, ready, repeat;
} ProcessBehavior;

Queue ArrivalQ;
Process IdleProcess;
int Clock = 0;

int process_compare(void *e1, void *e2) {
  Process *s1=(Process *)e1;
  Process *s2=(Process *)e2;
  if (s1->priority > s2->priority) {
    return 0;
  }
  else{
    return 1;
  }
}

void read_process_descriptions(void){
  Process p;
  ProcessBehavior b;
  int pid = 0, first = 1;
  unsigned long arrival;

  init_process(&p);
  arrival = 0;
  while (scanf("%lu", &arrival) != EOF){
    scanf("%d %lu %lu %d", 
        &pid,
        &b.cpuburst,
        &b.ioburst,
        &b.repeat);

    if (!first && p.pid != pid){
      add_to_queue(&ArrivalQ, &p, p.arrival_time);
      init_process(&p);
    }
    p.pid = pid;
    p.arrival_time = arrival;
    first=0;
    add_to_queue(&p.behaviors, &b, 1);
  }
  add_to_queue(&ArrivalQ, &p, p.arrival_time);
}

void nolock_add_to_queue(Queue *q, void *element, int priority){

  Queue_element new_element, ptr, prev = 0;
  if (!q->queue ||
      (q->queue && (q->duplicates || !nolock_element_in_queue(q, element)))) {

        new_element = (Queue_element) malloc(sizeof(struct _Queue_element));
        if (new_element == 0){
          fprintf(stderr, "Malloc failed in function add_to_queue()\n");
          exit(1);
        }
        new_element->info = (void *)malloc(q->elementsize);
        if (new_element->info == 0){
          fprintf(stderr, "Malloc failed in function add_to_queue()\n");
          exit(1);
        }
        memcpy(new_element->info, element, q->elementsize);

        new_element->priority = priority;
        (q->queuelength)++;
        if(q->queue == 0){
          //somestuffhere
        }
      }
}

int main(int argc, char *argv[]) {
  init_all_queues();
  init_process(&IdleProcess);
  read_process_descriptions();

  while (processes_exist()){
    Clock++;
    queue_new_arrivals();
    execute_highest_priority_process();
    do_IO();
  }

  Clock++;
  final_report();

  return 0;
}
