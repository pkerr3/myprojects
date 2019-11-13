package forestanalyzer;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Performs insertions and searches, using the same data set,on a binary search
 * tree and an AVL tree to compare the performance of
 * these operations on the trees.
 * @author Duncan, Kerr
 * Course: CS3102.01<br>
 * Programming Project #: 2<br>
 * Instructor: Dr. Duncan<br>
 * @SEE AVLTree, AVLTreeException, BSTree, BSTreeException, 
 * @since 10-14-2018
 */
public class ForestAnalyzer 
{ 
    /**
     * @param args the command line arguments
     * @throws AVLTreeException
     * @throws BSTreeException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws AVLTreeException, BSTreeException, IOException
    {       
        String file = args[0];
        Scanner in = new Scanner(new FileReader(file));
        AVLTree<String> avl = new AVLTree();
        BSTree<String> bst = new BSTree();
        String command, word;
        Function<String, PrintStream> print = x -> System.out.printf("%-20s%n", x);
        while (in.hasNext())
        {
            command = in.next();
            if (command.equalsIgnoreCase("insert"))
            {
                word = in.next().toUpperCase();
                avl.insert(word);
                bst.insert(word);
                System.out.println("inserted " + word + " in the AVL");
                System.out.println("inserted " + word + " in the BST");
                System.out.println("Type: size height diameter full? perfect?");
                System.out.printf("AVL:  %-2d   %-2d     %-2d       %-5s %-5s%n", avl.size(), 
                        avl.height(), avl.diameter(), avl.isFull(), avl.isPerfect());
                System.out.printf("BST:  %-2d   %-2d     %-2d       %-5s %-5s%n", bst.size(), 
                        bst.height(), bst.diameter(), bst.isFull(), bst.isPerfect());
                System.out.println();
            }
            else if (command.equalsIgnoreCase("remove"))
            {
                word = in.next().toUpperCase();
                System.out.println("removed " + word + " at depth " + avl.depth(word) + " in the AVL");
                System.out.println("removed " + word + " at depth " + bst.depth(word) + " in the BST");
                avl.remove(word);
                bst.remove(word);
                System.out.println("Type: size height diameter full? perfect?");
                System.out.printf("AVL:  %-2d   %-2d     %-2d       %-5s %-5s%n", avl.size(), 
                        avl.height(), avl.diameter(), avl.isFull(), avl.isPerfect());
                System.out.printf("BST:  %-2d   %-2d     %-2d       %-5s %-5s%n", bst.size(), 
                        bst.height(), bst.diameter(), bst.isFull(), bst.isPerfect());
                System.out.println();
            }
            else if (command.equalsIgnoreCase("traverse"))
            {
                System.out.println("AVL (in-order):");
                avl.traverse(print);
                System.out.println("--------------------------------");
                System.out.printf("word count: %d\n\n", avl.size());
                System.out.println("BST (in-order):");
                bst.traverse(print);
                System.out.println("--------------------------------");
                System.out.printf("word count: %d\n\n", bst.size());
            }
        
    }
        in.close();
}
}
