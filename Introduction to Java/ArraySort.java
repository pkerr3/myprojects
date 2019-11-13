/*
 * Sorts an Array Using Different Methods and Then Compares the time It Takes
 * CSC 1350 Lab #8
 * @author Phillip Kerr
 * @since November 8, 2017
 */
package arraysort;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;


public class ArraySort {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Array of size: ");
        int t = in.nextInt();
        int[] arrayToSort = new int[t];
        PrintWriter out = new PrintWriter("output.txt");
        out.println("Array of size: " + t);
        for (int i = 0; i < arrayToSort.length; i++)
        {
            int n = rand.nextInt(1000);
            arrayToSort[i] = n;
        }
        out.println(Arrays.toString(arrayToSort));
        //Selection Sort
        long selection1 = System.nanoTime();
        selectionSort(arrayToSort);
        long selection2 = System.nanoTime();
        out.println(Arrays.toString(arrayToSort));
        out.println("Selection sort run time: " + (selection2-selection1) + " nanoseconds.");
        
        //Insertion Sort
        long insertion1 = System.nanoTime();
        insertionSort(arrayToSort);
        long insertion2 = System.nanoTime();
        out.println("Insertion sort run time: " + (insertion2-insertion1) + " nanoseconds.");
        
        //Merge Sort
        long merge1 = System.nanoTime();
        mergesort(arrayToSort, 0, arrayToSort.length-1);
        long merge2 = System.nanoTime();
        out.println("Merge sort run time: " + (merge2-merge1) + " nanoseconds.");
        
        //Quick Sort
        long quick1 = System.nanoTime();
        mergesort(arrayToSort, 0, arrayToSort.length-1);
        long quick2 = System.nanoTime();
        out.println("Quick sort run time: " + (quick2-quick1) + " nanoseconds.");
        
        //finds time for use in the if statements
        long selection = (selection2-selection1);
        long insertion = (insertion2-insertion1);
        long merge = (merge2-merge1);
        long quick = (quick2-quick1);
        
        //if selection is the fastest
        if (selection <= insertion && selection <= merge && selection <= quick)
        {
            double vsQuick = (float)quick/selection;
            double vsInsertion = (float)insertion/selection;
            double vsMerge = (float)merge/selection;
            out.printf("Selection Sort was %.1f times faster than Quick Sort, %.1f times faster than Insertion Sort, and %.1f times faster than Merge Sort.", vsQuick, vsInsertion, vsMerge);
        }
        
        //if insertion is the fastest
        if (insertion <= selection && insertion <= quick && insertion <= merge)
        {
          double vsQuick = (float)quick/insertion;
          double vsSelection = (float)selection/insertion;
          double vsMerge = (float)merge/insertion;
          out.printf("Insertion Sort was %.1f times faster than Quick Sort, %.1f times faster than Selection Sort, and %.1f times faster than Merge Sort.", vsQuick, vsSelection, vsMerge);
        }
        
        //if merge is the fastest
        if (merge <= selection && merge <= insertion && merge <= quick)
        {
            double vsQuick = (float)quick/merge;
            double vsInsertion = (float)insertion/merge;
            double vsSelection = (float)selection/merge;
            out.printf("Merge Sort was %.1f times faster than Quick Sort, %.1f times faster than Insertion Sort, and %.1f times faster than Selection Sort.", vsQuick, vsInsertion, vsSelection);
        }
        
        //if quick is the fastest
        if (quick <= selection && quick <= merge && quick <= insertion)
        {
            double vsSelection = (float)selection/quick;
            double vsInsertion = (float)insertion/quick;
            double vsMerge = (float)merge/quick;
            out.printf("Quick Sort was %.1f times faster than Selection Sort, %.1f times faster than Insertion Sort, and %.1f times faster than Merge Sort.", vsSelection, vsInsertion, vsMerge);
        }
        out.close();
    }
    
        //Selection Sort Method
    public static void selectionSort(int[] arrayToSort) 
    {
        int selectionTemp = 0;
        int selectionBeg = 0, selectionMin, selectionMinPos;
        for(int j = selectionBeg; j < arrayToSort.length; j++)
        {
            selectionMin = arrayToSort[j];
            selectionMinPos = j;
            for(int i = j+1; i < arrayToSort.length; i++)
            {
                if(arrayToSort[i] < selectionMin)
                {
                    selectionMin = arrayToSort[i];
                    selectionMinPos = i;
                }
            }
            selectionTemp = arrayToSort[j];
            arrayToSort[j] = arrayToSort[selectionMinPos];
            arrayToSort[selectionMinPos] = selectionTemp;
        }
    }
        //Merge Sort methods
        public static void merge(int[] arrayToSort, int low, int med, int high)
        {
            //get the elements in the two parts of the array
            int size1 = med - low + 1;
            int sizeRight = high - med;
            //set the size of the two array halves
            int[] Left = new int[size1];
            int[] Right = new int[sizeRight];
            //loop to copy elements into new, half arrays
            for (int i = 0; i < size1; i++)
            {
                Left[i] = arrayToSort[low+i];
            }
            for (int j = 0; j < sizeRight; j++)
            {
                Right[j] = arrayToSort[med+1+j];
            }
            int i = 0, j = 0, mergePosition = low;
            //merge the two half arrays while comparing the first element in each
            while (i < size1 && j < sizeRight)
            {
                if (Left[i] <= Right[j])
                {
                    arrayToSort[mergePosition] = Left[i];
                    i++;
                }
                else
                {
                    arrayToSort[mergePosition] = Right[j];
                    j++;
                }
                mergePosition++;
            }
            while (i < size1)
            {
                arrayToSort[mergePosition] = Left[i];
                i++;
                mergePosition++;
            }
            while (j < sizeRight)
            {
                arrayToSort[mergePosition] = Right[j];
                j++;
                mergePosition++;
            }
        }
        public static void mergesort(int[] arrayToSort, int low, int high)
        {
            if (low < high)
            {
                int mid = (low+high)/2;
                mergesort(arrayToSort, low, mid);
                mergesort (arrayToSort, mid+1, high);
                
                merge(arrayToSort, low, mid, high);
            }
        }
        //Quick Sort Methods
        public static void quicksort(int[] arrayToSort, int low, int high)
        {
            int p;
            if (low < high)
            {
                p = partition(arrayToSort, low, high);
                quicksort(arrayToSort, low, p-1);
                quicksort(arrayToSort, p+1, high);
            }
        }
        public static int partition(int[] arrayToSort, int low, int high)
        {
            int pivot = arrayToSort[high];
            int i = low-1, quickSortTemp = 0;
            for (int j = low; j < high; j++)
            {
                if (arrayToSort[j] < pivot)
                        {
                            i = i+1;
                            quickSortTemp = arrayToSort[i];
                            arrayToSort[i] = arrayToSort[j];
                            arrayToSort[j] = quickSortTemp;
                        }
            }
            if(arrayToSort[high] < arrayToSort[i+1])
            {
                quickSortTemp = arrayToSort[high];
                arrayToSort[high] = arrayToSort[i+1];
                arrayToSort[i+1] = quickSortTemp;
            }
            return i+1;
        }
        
        public static void insertionSort(int[] arrayToSort)
        {
        int insertionTemp = 0;
        for (int i = 1; i < arrayToSort.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (arrayToSort[j] < arrayToSort[j-1])
                {
                    insertionTemp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j-1];
                    arrayToSort[j-1] = insertionTemp;
                }
            }
        }
        }
}




