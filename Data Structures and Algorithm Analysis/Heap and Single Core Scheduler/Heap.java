package singlecorescheduler;

import java.util.*;

/**
 * This class models an array-list-based min binary heap that implements the 
 * HeapAPI interface. The array holds objects that implement the 
 * parameterized Comparable interface.
 * @author Duncan, Phillip Kerr
 * @param <E> the heap element type. 
 * @since 9-23-2018
 */
public class Heap<E extends Comparable<E>> implements HeapAPI<E>
{    
   /**
    * A complete tree stored in an array list representing this 
    * binary heap
    */
   private ArrayList<E> tree;
   /**
    * A comparator lambda function that compares two elements of this
    * heap when rebuilding it; cmp.compare(x,y) gives 1. negative when x less than y
    * 2. positive when x greater than y 3. 0 when x equal y
    */   
   private Comparator<? super E> cmp;
   
   
   /**
    * Constructs an empty heap using the compareTo method of its data type as the 
	* comparator
    */
   
   public Heap()
   {
       tree = new ArrayList<E>();
       cmp = (e1, e2) ->
       {
           return e1.compareTo(e2);
       };
   }
   
   /**
    * A parameterized constructor that uses an externally defined comparator    
    * @param fn - a trichotomous integer value comparator function   
    */
   public Heap(Comparator<? super E> fn)
   {
       cmp = fn;
       tree = new ArrayList<E>();
   }

   public boolean isEmpty()
   {
       return tree.isEmpty();
   }

   public void insert(E obj)
   {
       tree.add(obj);
       int place = size()-1;
       int parent = (place-1)/2;
       while (parent >= 0 && (cmp.compare(tree.get(place), tree.get(parent)) > 0))
       {
           swap(parent, place);
           place = parent;
           parent = (place-1)/2;
       }
   }

   public E remove() throws HeapException
   {
       if (tree.isEmpty())
           throw new HeapException("Empty tree called!");
       E root = tree.get(0);
       tree.set(0, tree.get(size()-1));
       tree.remove(size()-1);
       rebuild(0, size());
       root = tree.get(0);
       return root;
   }
 
   public E peek() throws HeapException
   {
      if (tree.isEmpty())
          throw new HeapException("Empty tree called!");
      return tree.get(0);
   }

   public int size()
   {
       if (!tree.isEmpty())
           return tree.size();
      return -1;
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
          E temp = tree.get(parent);
          tree.set(parent, tree.get(place));
          tree.set(place, temp);
   }

   /**
    * Rebuilds the heap to ensure that the heap property of the tree is preserved.
    * @param root the root index of the subtree to be rebuilt
    * @param eSize the size of this tree
    */
   private void rebuild(int root, int eSize)
   {
       int child;
       if (2*root + 1 < size())
       {
           child = 2*root + 1;
           if (child + 1 < size())
           {
               if (tree.get(child).compareTo(tree.get(child+1)) < 0)
                   child++;
               if (tree.get(root).compareTo(tree.get(child)) < 0)
               {
                   swap(root, child);
                   rebuild(child, eSize);
               }       
           }
       }     
   }
}
