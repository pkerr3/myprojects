package singlecorescheduler;

/**
 * A application to simulate a non-preemptive scheduler for a single-core CPU
 * using a heap-based implementation of a priority queue
 * @author William Duncan, YOUR NAME
 * @since 9-24-2018
 * @see Heap.java, PCB.java
 * File:SingleCoreScheduler.java
 */


import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class SingleCoreScheduler 
{
    /**
     * Single-core processor with non-preemptive scheduling simulator    
     * @param args an array of strings containing command line arguments
     * args[0] - number of cycles to run the simulation
     * args[1] - the mode: -r or -R for random mode and -f or -F for file mode
     * args[2] - if the mode is random, this entry contains the probability that
     * a process is created per cycle and if the simulator is running in
     *           file mode, this entry contains the name of the file containing the 
     *           the simulated jobs. In file mode, each line of the input file is  
     *           in this format: 
     * <process ID> <priority value> <cycle of process creation> <time required to execute> 
     */
    public static void main(String []args) throws HeapException, IOException
    {
            Comparator<PCB> cmp = (x, y)->
            {
                if (x.isExecuting())
                    return 1;
                if (y.isExecuting())
                    return -1;
                if (x.getPriority() < y.getPriority())
                    return 1;
                if (x.getPriority() > y.getPriority())
                    return -1;
                if (x.getArrival() > y.getArrival())
                    return 1;
                if (x.getArrival() < y.getArrival())
                    return -1;
                return 0;
            };  
        int pid = 0, priority = 0, arrived, length = 0, wait = 0, pc = 1, cycles;
        Heap<PCB> readyq = new Heap(cmp);
        Random rand = new Random(System.currentTimeMillis());
        int max = Integer.parseInt(args[0]);
        if (args[1].equalsIgnoreCase("-r"))
        {
            double prob = Double.parseDouble(args[2]);
            for(cycles = 0; cycles < max; cycles++)
            {
                System.out.printf("*** Cycle #: %d ***%n", cycles);
                if (readyq.isEmpty())
                    System.out.println("The CPU is idle.");
                else
                {
                if (!readyq.peek().isExecuting())
                    {
                        readyq.peek().execute();
                        readyq.peek().setStart(cycles);
                        readyq.peek().setWait(cycles + readyq.peek().getLength());
                        wait += readyq.peek().getWait();
                    }
                if (cycles == readyq.peek().getWait())
                    {
                        System.out.printf("Process #%d has just terminated.%n", readyq.peek().getPid());
                        readyq.remove();
                        pc++;
                    }
                    else
                        System.out.printf("Process #%d is executing.%n", readyq.peek().getPid());
                }
             if (rand.nextDouble() < prob)
                {
                    pid++;
                    priority = rand.nextInt(40)-19;
                    length = rand.nextInt(100)+1;
                    readyq.insert(new PCB(pid, priority, 0, cycles, length));
                    System.out.printf("Adding job with pid #%d and priority %d "
                            + "and length %d.%n", pid, priority, length);
                }
                else
                    System.out.println("No new job this cycle.");
            
            }
        System.out.println("Number of processes executed: " + pc + ".");
        System.out.printf("Average throughput is %f per cycle.%n", (double)pc/(double)cycles);
        System.out.printf("The average waittime is %f%n.", (double)wait/(double)pc);
        }
          if (args[1].equalsIgnoreCase("-f"))
          {
            Scanner input = new Scanner((new FileReader(args[2])));
            pid = input.nextInt();
            priority = input.nextInt();
            arrived = input.nextInt();
            length = input.nextInt();
            for (cycles = 0; cycles < max; cycles++)
            {
                System.out.printf("*** Cycle #: %d ***%n", cycles);
                if (readyq.isEmpty())
                    System.out.println("The CPU is idle.");
                else
                {
                if (!readyq.peek().isExecuting())
                    {
                        readyq.peek().execute();
                        readyq.peek().setStart(cycles);
                        readyq.peek().setWait(cycles - readyq.peek().getArrival());
                        wait += readyq.peek().getWait();
                    }
                    if (readyq.peek().getLength() + readyq.peek().getStart() == cycles)
                    {
                        PCB placeHold = readyq.remove();
                        System.out.printf("Process #%d has just terminated.%n", 
                                placeHold.getPid());
                        pc++;
                    }
                    else
                        System.out.printf("Process #%d is executing.%n", 
                                readyq.peek().getPid());
                }
                if (cycles == arrived)
                {
                    readyq.insert(new PCB(pid, priority, 0, arrived, length));
                    System.out.printf("Adding job with pid #%d and priority %d "
                            + "and length %d.%n", pid, priority, length);
                    if (input.hasNext())
                    {
                        pid = input.nextInt();
                        priority = input.nextInt();
                        arrived = input.nextInt();
                        length = input.nextInt();
                    }
                }
                else
                    System.out.println("No new jobs this cycle.");
            }
        System.out.println("Number of processes executed: " + pc + ".");
        System.out.printf("Average throughput is %f per cycle.%n", (double)pc/(double)cycles);
        System.out.printf("The average waittime is %f.%n", (double)wait/(double)pc);
    }
}
    }













