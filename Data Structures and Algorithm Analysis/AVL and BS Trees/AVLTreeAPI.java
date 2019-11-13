package forestanalyzer;
import java.util.function.Function;

/**
 * Reports an exception in an AVL Tree
 * Course: CS3102.01<br>
 * Programming Project #: 2<br>
 * Instructor: Dr. Duncan<br>
 * @author Duncan
 * @since 10-14-2018
 */
class AVLTreeException extends Exception 
{

    /**
     * Creates a new instance of <code>AVLTreeException</code> without detail
     * message.
     */
    public AVLTreeException() { }

    /**
     * Constructs an instance of <code>AVLTreeException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public AVLTreeException(String msg) 
    {
        super(msg);
    }
}

/**
 * Describes operations on an AVLTree
 * Course: CS3102.01<br>
 * Programming Project #: 2<br>
 * Instructor: Dr. Duncan<br>
 * @param <E> the data type
 * @author Duncan
 * @since 10-14-2018
 * @see AVLTreeException
 */
public interface AVLTreeAPI<E extends Comparable<E>>
{
   /**
    * Determines whether the tree is empty.
    * @return true if the tree is empty;  otherwise, false
   */
   boolean isEmpty();

   /**
    * Inserts an item into the tree.
    * @param obj the value to be inserted.
    */
   void insert(E obj);

   /**
    * Determine whether an item is in the tree.
    * @param item item with a specified search key.
    * @return true on success; false on failure.
    */
   boolean inTree(E item);

   /**
    * Delete an item from the tree.
    * @param item item with a specified search key.
   */
   void remove(E item);

   /**
    * returns the item with the given search key.
    * @param key the key of the item to be retrieved
    * @return the item with the specified key
    * @throws AVLTreeException when no such element exists 
    */
   E retrieve(E key) throws AVLTreeException;

   /**
    * This function traverses the tree in in-order
    * and calls the function Visit once for each node.
    * @param func the function to apply to the data in each node
    */
   void traverse(Function func);
   
   /**
    * Returns the number of items stored in the tree.
    * @return the size of the tree.
    */
   int size();
}