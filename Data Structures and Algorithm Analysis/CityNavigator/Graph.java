package graphdemo;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Implementation of an extensible adjacency list representation 
 * of a weighted digraph whose vertices hold objects that implement 
 * the Comparable interface.
 * @param <E> the data type
 * @author Duncan, Kerr
 * @since 10-29-2018
 * Programming Project #3
 * Instructor: Dr. Duncan
 */
public class Graph<E extends Comparable<E>> implements GraphAPI<E>
{
    /*
     * number of vertices (size of this graph)
     */
    private long order;
    /**
     * pointer to the list of vertices
     */
    private Vertex first;
   
   /**
    * A vertex of a graph stores a data item and references
    * to its edge list and the succeeding vertex. The data
    * object extends the comparable interface.
    */      
   private class Vertex
   {
       /**
        * pointer to the next vertex
        */
       public Vertex pNextVertex;
       /**
        * the data item
        */
       public E data;
       /**
        * in-degree
        */
       public long inDeg;
       /**
        * out-degree
        */
       public long outDeg;
       /**
        * pointer to the edge list
        */
      public Edge pEdge;
      /**
       * Field for tracking vertex accesses
       */
      public long processed;
   }
   
   /** 
    * An edge of a graph contains a reference to the destination
    * vertex, a reference to the succeeding edge in the edge list and
    * the weight of the directed edge.
    */   
   private class Edge
   {
       /**
        * pointer to the destination vertex
        */
       public Vertex destination;
       /**
        * weight on this edge
        */
       public Double weight;
       /**
        * pointer to the next edge
        */
       public Edge pNextEdge;
   }   
   /**
    * Constructs an empty weighted directed graph
   */
   public Graph()
   {
      first = null;
      order = 0;
   }

   @Override
   public void insertVertex(E obj)
   {
      Vertex locPtr = first;
      Vertex predPtr = null;
      while (locPtr != null && obj.compareTo(locPtr.data) > 0)
      {
         predPtr = locPtr;
         locPtr = locPtr.pNextVertex;
      }
      /*key already exist. */
      if (locPtr != null && obj.compareTo(locPtr.data)==0)
      {
         locPtr.data = obj;
         return;
      }
      Vertex newPtr = new Vertex();
      newPtr.pNextVertex = null;
      newPtr.data  = obj;
      newPtr.inDeg = 0;    
      newPtr.outDeg = 0;    
      newPtr.processed = 0;
      newPtr.pEdge = null;      
      /* insert before first vertex */
      if (predPtr == null)
         first = newPtr;
      else
         predPtr.pNextVertex = newPtr;
      newPtr.pNextVertex = locPtr;   
      order++;
   }

   @Override
   public void deleteVertex(E key)
   {
      if (isEmpty())
         return;
      Vertex predPtr = null;
      Vertex walkPtr = first;
      while (walkPtr != null && key.compareTo(walkPtr.data) > 0)
      {
         predPtr = walkPtr;
         walkPtr = walkPtr.pNextVertex;
      }
      if (walkPtr == null || key.compareTo(walkPtr.data) != 0)
         return;
      /* vertex found. Test degree */
      if ((walkPtr.inDeg > 0) || (walkPtr.outDeg > 0))
         return;
      /* OK to delete */
      if (predPtr == null)
         first = walkPtr.pNextVertex;
      else
         predPtr.pNextVertex = walkPtr.pNextVertex;
      order--;
   }

   @Override
   public void insertEdge(E fromKey, E toKey, Double weight)
   {      
      if (isEmpty())
         return;
      Edge pred;   
      /* check whether originating vertex exists */
      Vertex tmpFrom = first;
      while (tmpFrom != null && fromKey.compareTo(tmpFrom.data) > 0)
         tmpFrom = tmpFrom.pNextVertex;
      if (tmpFrom == null || fromKey.compareTo(tmpFrom.data) != 0)
         return;
      /* locate destination vertex */
      Vertex tmpTo = first;
      while (tmpTo != null && toKey.compareTo(tmpTo.data) > 0)
         tmpTo = tmpTo.pNextVertex;
      if (tmpTo == null || toKey.compareTo(tmpTo.data) != 0)
         return;
      /*check if edge already exists */
      Edge tmpEdge = tmpFrom.pEdge;
      while (tmpEdge != null && tmpEdge.destination != tmpTo)
         tmpEdge = tmpEdge.pNextEdge;
      if (tmpEdge != null && tmpEdge.destination != null)
         return;
      tmpFrom.outDeg++;
      tmpTo.inDeg++;
      Edge newEdge = new Edge();
      newEdge.destination = tmpTo;
      newEdge.weight = weight;
      newEdge.pNextEdge = null;
      if (tmpFrom.pEdge == null)
      {
         tmpFrom.pEdge = newEdge;
         return;
      }
      pred = null;
      tmpEdge = tmpFrom.pEdge;
      while (tmpEdge != null && tmpEdge.destination != tmpTo)
      {
         pred = tmpEdge;
         tmpEdge = tmpEdge.pNextEdge;
      }
      if (pred == null)
         tmpFrom.pEdge = newEdge;
      else
         pred.pNextEdge = newEdge;
      newEdge.pNextEdge = tmpEdge;
   }         

   @Override
   public void deleteEdge(E fromKey, E toKey)
   {
      /* find source vertex */
      Vertex tmpFrom = first;
      while (tmpFrom != null && fromKey.compareTo(tmpFrom.data)>0)
         tmpFrom = tmpFrom.pNextVertex;
      if (tmpFrom == null || fromKey.compareTo(tmpFrom.data) != 0)
         return;
      /* locate destination vertex */
      Vertex tmpTo = first;
      while (tmpTo != null && toKey.compareTo(tmpTo.data)>0)
         tmpTo = tmpTo.pNextVertex;
      if (tmpTo == null || toKey.compareTo(tmpTo.data) != 0)
         return;
      /*check if edge does not exist */
      Edge tmpEdge = tmpFrom.pEdge;
      Edge pred = null;
      while (tmpEdge != null && tmpEdge.destination != tmpTo)
      {
         pred = tmpEdge;
         tmpEdge = tmpEdge.pNextEdge;
      }
      /* if edge does not exist */
      if (tmpEdge == null)
         return;
      /* update degrees */
      if (pred != null)
          pred.pNextEdge = tmpEdge.pNextEdge;
      tmpFrom.outDeg--;
      tmpTo.inDeg--;
   }

   @Override
   public double retrieveEdge(E fromKey, E toKey) throws GraphException
   {
      /* find source vertex */
      Vertex tmpFrom = first;
      while (tmpFrom != null && fromKey.compareTo(tmpFrom.data)>0)
         tmpFrom = tmpFrom.pNextVertex;
      if (tmpFrom == null || fromKey.compareTo(tmpFrom.data) != 0)
         throw new GraphException("Non-existent edge - retrieveEdge().");
      /* locate destination vertex */
      Vertex tmpTo = first;
      while (tmpTo != null && toKey.compareTo(tmpTo.data)>0)
         tmpTo = tmpTo.pNextVertex;
      if (tmpTo == null || toKey.compareTo(tmpTo.data) != 0)
         throw new GraphException("Non-existent edge - retrieveEdge().");
      /*check if edge does not exist */
      Edge tmpEdge = tmpFrom.pEdge;
      while (tmpEdge != null && tmpEdge.destination != tmpTo)
         tmpEdge = tmpEdge.pNextEdge;
      /* if edge does not exist */
      if (tmpEdge == null)
         throw new GraphException("Non-existent edge - retrieveEdge().");
      return tmpEdge.weight;
   }

   @Override
   public E retrieveVertex(E key) throws GraphException
   {
      if (isEmpty())
         throw new GraphException("Non-existent vertex - retrieveVertex().");
      Vertex tmp = first;
      while (tmp != null && key.compareTo(tmp.data) != 0)
         tmp = tmp.pNextVertex;
      if (tmp == null)
         throw new GraphException("Non-existent vertex - retrieveVertex().");
      return tmp.data;
   }

   @Override
   public void bfsTraverse(Function func)
   {
      if (isEmpty())
         return;
      Vertex walkPtr = first;
      while (walkPtr != null)
      {
         walkPtr.processed = 0;
         walkPtr = walkPtr.pNextVertex;
      }
      ArrayList<Vertex> queue = new ArrayList();
      Vertex toPtr;
      Edge edgeWalk;
      Vertex tmp;      
      walkPtr = first;
      while (walkPtr != null)
      {
         if (walkPtr.processed < 2)
         {
            if (walkPtr.processed < 1)
            {
               queue.add(walkPtr);
               walkPtr.processed = 1;
            }
         }
         while (!queue.isEmpty())
         {
            tmp = queue.remove(0);
            func.apply(tmp.data);
            tmp.processed = 2;
            edgeWalk = tmp.pEdge;
            while (edgeWalk != null)
            {
               toPtr = edgeWalk.destination;
               if (toPtr.processed == 0)
               {
                  toPtr.processed = 1;
                  queue.add(toPtr);
               }
               edgeWalk = edgeWalk.pNextEdge;
            }            
         }
         walkPtr = walkPtr.pNextVertex;      
      }
   }
   
   @Override
   public void dfsTraverse(Function func)
   {
      if (isEmpty())
         return;
      Vertex walkPtr = first;
      while(walkPtr != null)
      {
         walkPtr.processed = 0;
         walkPtr = walkPtr.pNextVertex;
      }
      ArrayList<Vertex> stack = new ArrayList();
      Vertex toPtr;      
      Edge edgeWalk;
      Vertex tmp;      
      walkPtr = first;
      while (walkPtr != null)
      {
         if (walkPtr.processed < 2)
         {
            if (walkPtr.processed < 1)
            {
               walkPtr.processed = 1;
               stack.add(0,walkPtr);
            }
            while (!stack.isEmpty())
            {
                tmp = stack.get(0);
                edgeWalk = tmp.pEdge;
                while(edgeWalk != null)
                {
                   toPtr = edgeWalk.destination;
                   if (toPtr.processed == 0)
                   {
                      toPtr.processed = 1;
                      stack.add(0,toPtr);
                      edgeWalk = toPtr.pEdge;
                   }
                   else
                      edgeWalk = edgeWalk.pNextEdge;
                }
                tmp = stack.remove(0);
                func.apply(tmp.data);
                tmp.processed = 2;
            }
         }
         walkPtr = walkPtr.pNextVertex;
      }            
   }

   @Override
   public boolean isEmpty()
   {
      return first == null;
   }

   @Override
   public long size()
   {
      return order;
   }

   @Override
   public boolean isVertex(E key)
   {
      if (isEmpty())
         return false;
      Vertex tmp = first;
      while (tmp != null && key.compareTo(tmp.data) != 0)
         tmp = tmp.pNextVertex;
      return tmp != null;      
   }
/*===> BEGIN: Augmented ADT methods <===*/  
   @Override
   public boolean isEdge(E fromKey, E toKey)
   {
       try 
       {
           retrieveEdge(fromKey, toKey);
       }
       catch (GraphException exception)
       {
           return false;
       }
      return true;
   }

   @Override
   public boolean isPath(E fromKey, E toKey)
   {
      if (isEmpty())
          return false;
      if (fromKey == toKey)
          return true;
      Vertex temp = first;
      while(temp != null)
      {
         temp.processed = 0;
         temp = temp.pNextVertex;
      }
      temp = first;
      while (temp != null && fromKey.compareTo(temp.data) != 0)
          temp = temp.pNextVertex;
      if (temp == null)
          return false;
      Queue q = new LinkedList();
      q.add(temp);
      while (!q.isEmpty())
      {
          temp = (Vertex) q.remove();
          if (temp.data.compareTo(toKey) == 0)
              return true;
          temp.processed = 2;
          Edge tempEdge = temp.pEdge;
          while (tempEdge != null)
          {
              if (tempEdge.destination.processed == 0)
              {
                  tempEdge.destination.processed = 1;
                  q.add(tempEdge.destination);
              }
              tempEdge = tempEdge.pNextEdge;
          }
      }
      return false;
   }

   @Override
   public long countEdges()
   {
       Vertex tmp = first.pNextVertex;
       long edges = first.inDeg;
       while (tmp != null)
       {
           edges += tmp.inDeg;
           tmp = tmp.pNextVertex;
       }
      return edges;
   }
/*===> END: Augmented ADT methods <===*/  
   @Override
   public long outDegree(E key) throws GraphException
   {
      if (isEmpty())
         throw new GraphException("Non-existent vertex - outDegree().");
      Vertex tmp = first;
      while (tmp != null && key.compareTo(tmp.data) != 0)
         tmp = tmp.pNextVertex;
      if (tmp == null)
         throw new GraphException("Non-existent vertex - outDegree().");
      return tmp.outDeg;
   }

   @Override
   public long inDegree(E key) throws GraphException
   {
      if (isEmpty())
         throw new GraphException("Non-existent vertex - inDegree().");
      Vertex tmp = first;
      while (tmp != null && key.compareTo(tmp.data) != 0)
         tmp = tmp.pNextVertex;
      if (tmp == null)
         throw new GraphException("Non-existent vertex - inDegree().");
      return tmp.inDeg;
   }    
}

