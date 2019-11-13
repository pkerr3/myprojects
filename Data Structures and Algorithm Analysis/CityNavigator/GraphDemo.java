package graphdemo;
/**
 * @file GraphDemo.java
 * @author Duncan, Kerr
 * @date 11-12-2018
 * Course: CS3102.01
 * Programming Project #4
 * Instructor: Dr. Duncan
 * Description: A test bed for the implementations of various 
 * weighted digraph algorithms
 * @see cities1.wdg, cities2.wdg, cities3.wdg, cities4.wdg, cities5.wdg
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.Comparator;
import java.util.PriorityQueue; 

public class GraphDemo
{
   public static final Double INFINITY = Double.POSITIVE_INFINITY;

   public static void main(String []args) throws GraphException
   {
      if (args.length != 1)
      {
         System.out.println("Usage: GraphDemo <filename>");
         System.exit(1);
      }
      City c1, c2;
      Scanner console;
      int menuReturnValue, i,j;
      Function<City,PrintStream> f = aCity -> System.out.printf("%-2d  %-30s%n",aCity.getKey(),aCity.getLabel().trim());      
      Graph<City> g = readGraph(args[0]);      
      Graph<City> gPrime;
      long s = g.size();
      menuReturnValue = -1;
      while (menuReturnValue != 0)
      {
         menuReturnValue = menu();
         switch(menuReturnValue)
         {
            case 1: //Transitive Closure Matrix of g'
               //Generate the transitive closure matrix
               gPrime = transpose(g);
               System.out.println();
               System.out.println("Transitive Closure Matrix for the Transpose of the Graph In " + args[0]);
               System.out.println("=========================================================================================");              
               for (int k = 1; k <= g.size(); k++)
               {
                   for (int l = 1; l <= g.size(); l++)
                   {
                       if (k == l)
                           System.out.print("1  ");
                       else if (gPrime.isPath(new City(k), new City(l)))
                           System.out.print("1  ");
                       else
                           System.out.print("0  ");
                   }   
                   System.out.println();
               }
               System.out.println("=========================================================================================");
               System.out.println();
               System.out.println();
               break;             
            case 2: //number of edges
               System.out.println();
               System.out.println("The graph has "+g.countEdges()+".");
               System.out.println();
               break;                      
            case 3://Shortest-path algorithm
               console = new Scanner(System.in);
               System.out.printf("Enter the source vertex: ");      
               int initial = console.nextInt();
               System.out.printf("Enter the destination vertex: ");      
               int dest = console.nextInt();
               if (g.isPath(new City(initial), new City(dest)))
               {
                  System.out.printf("Shortest route from %s to %s in G:%n",g.retrieveVertex(new City(initial)).getLabel().trim(),g.retrieveVertex(new City(dest)).getLabel().trim());				   
                  System.out.println("=========================================================================================");
                  double[] dist = new double[(int)g.size()];
                  int[] pred = new int[(int)g.size()];
                  dijkstra(g, dist, pred, initial, dest);
                  i = initial;
                  while (i != dest)
                  {
                      j = dest;
                      while (pred[j-1] != i)
                          j = pred[j-1];
                  System.out.printf("%-30s -> ", g.retrieveVertex(new City(i)).getLabel().trim());
                  System.out.printf("%-30s", g.retrieveVertex(new City(j)).getLabel().trim());
                  System.out.printf("%.2f mi%n", g.retrieveEdge(new City(i), new City(j)));
                  i = j;
                  }
                  System.out.println("=========================================================================================");
                  System.out.printf("Total distance: %f miles.%n%n",dist[dest-1]);                   
               }
               else
                  System.out.printf("There is no path.%n%n");
               break;
            case 4: //post-order depth-first-search traversal of g'
               System.out.println();
               System.out.println("PostOrder DFS Traversal For The Graph In "+args[0]);
               System.out.println("==========================================================================");
               gPrime = transpose(g);
               gPrime.dfsTraverse(f);
               System.out.println("==========================================================================");
               System.out.println();
               System.out.println();
               break;
            case 5: //breadth-first-search traversal
               System.out.println();
               System.out.println("BFS Traversal For The Graph In "+args[0]);
               System.out.println("==========================================================================");
               g.bfsTraverse(f);
               System.out.println("==========================================================================");
               System.out.println();
               System.out.println();
               break;
            case 6: //Check whether g is connected
               System.out.println();
               if (isConnected(g))
                  System.out.println("The graph is connected.");
               else
                   System.out.println("The graph is not connected.");
               System.out.println();
               break;                         
            case 7: //topoSort
               System.out.println();
               int[] top = new int[(int)g.size()];
               if (topSortOutDeg(g,top))
               {
                   System.out.println("Topological Sorting of The Graph In "+args[0]);
                   System.out.println("==========================================================================");           
                   for (i=1; i<=g.size(); i++)
                   {
                       c1 = g.retrieveVertex(new City(top[i-1]));
                       f.apply(c1);
                   }
                   System.out.println("==========================================================================");
               }
               else
                   System.out.println("No topological ordering possible. The digraph in "+args[0]+" contains a directed cycle.");
               System.out.printf("%n%n");
               break;
            case 8: //kruskalMST;
               int[] mst = new int[(int)g.size()];
               
               int[] mstK = new int[(int)g.size()];
               double totalWt = kruskalMST(g,mstK);
               String cityNameA,cityNameB;
               for (i=1; i<=g.size(); i++)
               {
                   if (mstK[i-1] < 1)
                       cityNameA = "NONE";
                   else
                       cityNameA = g.retrieveVertex(new City(mstK[i-1])).getLabel().trim();
                   cityNameB = g.retrieveVertex(new City(i)).getLabel().trim();
                       
                   System.out.printf("%d-%s parent[%d] <- %d (%s)%n",i,cityNameB,i,mstK[i-1],cityNameA);
               }
               System.out.printf("The weight of the minimum spanning tree/forest is %.2f miles.%n%n",totalWt);    
               break;
            default: ;
         } //end switch
      }//end while
   }//end main

   /**
    * This method reads a text file formatted as described in the project description.
    * @param filename the name of the DIMACS formatted graph file.
    * @return an instance of a graph.
    */
   private static Graph<City> readGraph(String filename)
   {
      try
      {
         Graph<City> newGraph = new Graph();
         try (FileReader reader = new FileReader(filename)) 
         {
            char temp;
            City c1, c2, aCity;
            String tmp;
            int k, m, v1, v2, j, size=0, nEdges=0;
            Integer key, v1Key,v2Key;
            Double weight;
            Scanner in = new Scanner(reader);
            while (in.hasNext())
            {
                tmp = in.next();
                temp = tmp.charAt(0);
                if (temp == 'p')
                {
                    size = in.nextInt();
                    nEdges = in.nextInt();
                }
                else if (temp == 'c')
                {
                    in.nextLine();
                }
                else if (temp == 'n')
                {
                    key = in.nextInt();
                    tmp = in.nextLine();
                    aCity = new City(key,tmp);
                    newGraph.insertVertex(aCity); 
                }
                else if (temp == 'e')
                {
                    v1Key = in.nextInt();
                    v2Key = in.nextInt();
                    weight = in.nextDouble();
                    c1 = new City(v1Key);
                    c2 = new City(v2Key);
                    newGraph.insertEdge(c1,c2,weight); 
                }
            }
         }
         return newGraph;
      }
      catch(IOException exception)
      {
            System.out.println("Error processing file: "+exception);
      }
      return null;
   } 

   /**
    * Display the menu interface for the application.
    * @return the menu option selected.
    */  
   private static int menu()
   {
      Scanner console = new Scanner(System.in);
      String option;
      do
      {
         System.out.println("  BASIC WEIGHTED GRAPH APPLICATION   ");
         System.out.println("=======================================================");
         System.out.println("[1] Transitive Closure Matrix of G Transpose");
         System.out.println("[2] Number of Edges in G");
         System.out.println("[3] Single-source Shortest Path in G");
         System.out.println("[4] Postorder DFS Traversal of G Transpose");
         System.out.println("[5] BFS Traversal of G");
         System.out.println("[6] Check the Connectivity of G");
         System.out.println("[7] Topological Sort Labeling");
         System.out.println("[8] Kruskal's Minimum Spanning Tree in G");
         System.out.println("[0] Quit");
         System.out.println("=====================================");
         System.out.printf("Select an option: ");         
         option = console.nextLine().trim();
         try
         {
             int choice = Integer.parseInt(option);
             if (choice < 0 || choice > 8)
             {
                System.out.println("Invalid option...Try again");
                System.out.println();
             }
             else
                return choice;
         }
         catch(NumberFormatException e)
         {
            System.out.println("Invalid option...Try again");
         }                           
      }while(true);
   }
   
   /**
    * This method creates the transpose graph of the specified graph,
    * that is a digraph whose vertices are the same but whose edges are the
    * reverse of those of the specified graph; this method should preserve
    * the specified graph and not mutate it while creating its transpose.
    * @param g a directed graph (without loops)
    * @return an instance of a graph representing the transpose of the 
    * specified graph
    * @throws GraphException
    */
   private static Graph<City> transpose(Graph<City> g) throws GraphException
   {   
       Graph<City> trans = new Graph<>();
       City city1, city2;
       for (int i = 1; i <= g.size(); i++)
       {
           trans.insertVertex(g.retrieveVertex(new City(i)));
       }
       for (int i = 1; i <= g.size(); i++)
       {
           city1 = g.retrieveVertex(new City(i));
            for (int j = 1; j <= g.size(); j++)
            {
                city2 = g.retrieveVertex(new City(j));
                if (g.isEdge(new City(i), new City(j)))
                {
                    trans.insertEdge(city2, city1, g.retrieveEdge(city1, city2));
                    trans.deleteEdge(city1, city2);
                }
            }
       }
       return trans;
   }
   
   /**
    * This method computes the cost and path arrays using the 
    * Dijkstra's single-source shortest path greedy algorithm.
    * @param g an instance of a weighted directed graph
    * @param dist an array containing shortest distances from a source vertex
    * @param pred an array containing predecessor vertices along the shortest path
    * @throws GraphException on call to retrieveEdge on non-existent edge
    */
   private static void dijkstra(Graph<City> g, double[] dist, int[] pred, int source, int destination) throws GraphException
   {
       /**
        * An auxiliary data structure to store the information
        * about a node for the Dijkstra SP algorithm
        * 
        */       
       class Node
       {
           public int id;
           public double key;
           public Node() {}
           public Node(int v, double k)
           {
               id = v;
               key = k;
           }           
       }
       /* A Node comparator */	   
       Comparator<Node> cmp = (v1, v2) -> 
       {
           double d = v1.key - v2.key;
           if (d < 0)
               return -1;
           if (d > 0)
               return 1;
           return v1.id - v2.id;         
       };
       PriorityQueue<Node> queue = new PriorityQueue(cmp);
       boolean[] processed = new boolean[(int) g.size()];
       for (int i = 0; i < dist.length; i++)
       {
           dist[i] = INFINITY;
           pred[i] = -1;
           if (i + 1 != source)
               queue.add(new Node(i + 1, INFINITY));
           else
               queue.add(new Node(i+1, 0));
       }
       dist[source-1] = 0;
       Node top;
       
       while (!queue.isEmpty() && queue.peek().id != destination)
       {
           top = queue.remove();
           if (!processed[top.id-1])
           {
               processed[top.id-1] = true;
               for(int v = 1; v <= g.size(); v++)
               {
                   if (!processed[v-1] && g.isEdge(new City(top.id), new City(v)))
                   {
                       double weight = g.retrieveEdge(new City(top.id), new City(v));
                       if (dist[top.id - 1] + weight < dist[v-1])
                       {
                           queue.add(new Node(v, dist[top.id-1] + weight));
                           pred[v-1] = top.id;
                           dist[v-1] = dist[top.id - 1] + weight;
                       }
                   }
               }
           }
       }
   }
   
   /**
    * Determines whether or not the specified undirected graph is connected
    * @return true if the specified graph is connected; otherwise, false
    * @throws GraphException 
    */
   private static boolean isConnected(Graph<City> g) throws GraphException
   {
    Queue<Integer> q = new LinkedList<>();
       boolean[] visited = new boolean[(int)g.size()];
       visited[0] = true;
       int numvisited = 1;
       int i = 1;
       for(int j = 2; j <= g.size(); j++)
       {
           if(g.isEdge(new City(i), new City(j)) == true || g.isEdge(new City(j), new City(i)) == true)
           {
               q.add(j);
               numvisited++;
               visited[j-1] = true;
           }
       }
       if(q.isEmpty())
       {
           return false;
       }
       while(!q.isEmpty())
       {
           for(int j = 2; j <= g.size(); j++)
           {
               if(visited[j-1] == false && (g.isEdge(new City(q.peek()),new City(j)) || g.isEdge(new City(j), new City(q.peek()))))
               {
                   q.add(j);
                   numvisited++;
                   visited[j-1] = true;
               } 
           }
           q.remove();
       }
       return numvisited == g.size();
   }
   
   /**
    * Generates a topological labeling of the specified digraph, in reverse order,
    * using the decrease-and-conquer algorithm that successively selects and 
    * removes a vertex of out-degree 0 until all the vertices are selected. 
    * The graph is explored in lexicographical order when adding a new vertex to the  
    * topological ordering and the graph is not modified. Updates of the degrees 
    * and vertices that are selected are tracked using auxiliary data structures. 
    * @param g a digraph
    * @param linearOrder the topological ordering of the vertices 
    * @return true if a topological ordering of the vertices of the specified digraph 
    * exists; otherwise, false. 
    */   
   private static boolean topSortOutDeg(Graph<City> g, int linearOrder[]) throws GraphException
   {
        int counter = (int)g.size() - 1, current = 0;
        boolean[] deleted = new boolean[(int)g.size()];
        int[] outDegrees = new int[(int)g.size()];
        for(int i = 0; i < g.size(); i++)
        {
            outDegrees[i] = (int)g.outDegree(new City(i + 1));
        }
        while(current < g.size())
        {
            if(deleted[current] == false)
            {
                if(outDegrees[current] == 0)
                {
                    if(counter == -1)
                    {
                        break;
                    }
                    for(int i = 1; i <= g.size();i++)
                    {
                        if(deleted[i-1] == false && i != current+1)
                        {
                            if(g.isEdge(new City(i), new City(current+1)))
                            {
                                outDegrees[i-1]--;
                            }
                        }    
                    }
                    deleted[current] = true;
                    linearOrder[counter] = current + 1;
                    counter--;
                    current = 0;
                }
                else
                {
                    current++;
                }
            }
            else
            {
                current++;
            }
        }
        return true;
    }
  
   /**
    * Find the root vertex of the tree in which the specified is
    * @param parent the parent implementation of a subtree of a graph
    * @param v a vertex
    * @return the root of this subtree
    */
   private static int find(int[] parent, int v)
   {
       if(parent[v - 1] != -1)
           v = find(parent, parent[v - 1]);
       return v;
   }
   /**
    * This method generates a minimum spanning tree using Kruskal's 
    * algorithm. If no such MST exists, then it generates a minimum spanning forest.
    * @param g a weighted directed graph
    * @param parent the parent implementation of the minimum spanning tree/forest
    * @return the weight of such a tree or forest.
    * @throws GraphException when this graph is empty
    * <pre>
    * {@code
    * If a minimum spanning tree cannot be generated,
    * the parent implementation of a minimum spanning tree or forest is
    * determined. This implementation is based on the union-find strategy.
    * }
    * </pre>
    */    
   private static double  kruskalMST(Graph<City> g, int [] parent) throws GraphException
   {
       /**
        * An auxiliary data structure to store the information
        * about an edge for the MST algorithm
        *
        */
       class EdgeType
       {
           public double weight;
           public int source;
           public int destination;
           public boolean chosen;
       }      
       /* An EdgeType comparator */
       Comparator<EdgeType> cmp = (t1, t2) ->
       {
           if (t1.weight < t2.weight)
               return -1;
           if (t1.weight > t2.weight)
               return 1;
           if (t1.source < t2.source)
               return -1;
           if (t1.source > t2.source)
               return 1;
           if (t1.destination < t2.destination)
               return -1;
           if (t1.destination > t2.destination)
               return 1;
           return 0;        
       };
      //implement this method      
      /*Defining an instance of the PriorityQueue class that uses the comparator above
        and complete the implementation of the algorithm */
        int n = (int)g.size();
        double totalWeight = 0;
        PriorityQueue<EdgeType> pQueue = new PriorityQueue(cmp);
        boolean seen[] =  new boolean[n];  
        EdgeType edge;
        for (int i =1; i <=n; i++)
        {
            parent[i-1]=-1;
            for (int j= i; j <=  n;j++)
            {
                if (g.isEdge(new City(i), new City(j)))
                {
                    edge = new EdgeType();
                    edge.weight= g.retrieveEdge(new City(i),new City(j));
                    edge.source=i;
                    edge.destination =j;
                    pQueue.add(edge);
                }
                else if (g.isEdge(new City(j), new City(i)))
                {
                    edge = new EdgeType();
                    edge.weight= g.retrieveEdge(new City(j),new City(i));
                    edge.source=j;
                    edge.destination =i;
                    pQueue.add(edge);
                }
            }
        }
        while (!pQueue.isEmpty())
        {
            edge=pQueue.poll();
            if ((seen[edge.source-1] == false) && (seen[edge.destination-1] == false))
            {
                totalWeight = totalWeight + edge.weight;
                seen[edge.destination-1] = true;
                seen[edge.source-1] =true;
                parent[edge.destination-1] =edge.source;
            }
            else if ((seen[edge.source-1] == true) && (seen[edge.destination-1] == false ))
            {
                totalWeight = totalWeight+ edge.weight;
                seen[edge.destination-1] = true;
                parent[edge.destination-1] =edge.source;
            }
            else if ((seen[edge.destination-1] == true) && (seen[edge.source-1] == false) )
            {
                totalWeight = totalWeight+ edge.weight;
                seen[edge.source-1] =true;
                parent[edge.source-1] =edge.destination;
            }
            else {
                if (find(parent,edge.source) != find(parent,edge.destination))
                {
                    totalWeight = totalWeight + edge.weight;
                    int child = edge.source;
                    int parent1 = edge.destination;
                    int grandParent = parent[parent1 - 1];
                    while (grandParent != -1)      
                    {
                        parent[parent1 - 1] =child;
                        child =parent1;
                        parent1 = grandParent;
                        grandParent = parent[parent1 - 1];
                    }
                    parent[parent1 - 1]=child;
                }
            }
        }
            return totalWeight;
   }          
}
