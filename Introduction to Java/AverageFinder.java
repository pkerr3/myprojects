/*
 * Calculates the Scores of Tests and their Averages in Certain Conditions
 * CSC 1350 Lab #6
 * @author Phillip Kerr
 * @since October 25, 2017
 */
package averagefinder;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;


public class AverageFinder {


    public static void main(String[] args) throws FileNotFoundException 
    {
     Scanner console = new Scanner(System.in);
     int students, lowest, gradeA = 0, totalSum, count, aboveSum, passingSum;
     double totalAverage = 0, aboveAverage = 0, passingAverage = 0;
     System.out.print("Please enter a number of students greater or equal to 5 --> ");
     students = console.nextInt();
     int[] testScores = new int[students];
     File inputFile = new File("input.txt");
     Scanner in = new Scanner(inputFile);
     PrintWriter out = new PrintWriter("output.txt");
     for (int i = 0; i < students; i++)
     {
         testScores[i] = in.nextInt();
         out.println("(" + (students - i) + " remaining.) -> " + testScores[i]);
         out.println("TestScores[" + i + "]" + " = " + testScores[i]);
         if (testScores[i] >= 90)
         {
             gradeA++;
         }
        
     }
     out.println("List of Scores: " + toString(testScores));
     totalSum = sumIf(testScores, 0, 100);
     count = countIf(testScores, 0, 100);
     totalAverage = totalSum*1.0/count;
     out.printf("Average Test Score: %d/%d = %.1f%n", totalSum, count, totalAverage);
     countIf(testScores, totalAverage, 100);
     aboveSum = sumIf(testScores, totalAverage, 100);
     count = countIf(testScores, totalAverage, 100);
     aboveAverage = aboveSum*1.0/count;
     out.printf("Average of Test Scores (>=%.1f): %d/%d = %.1f%n", totalAverage, aboveSum, count, aboveAverage);
     passingSum = sumIf(testScores, 70, 100);
     count = countIf(testScores, 70, 100);
     passingAverage = passingSum*1.0/count;
     out.printf("Average of Passing Test Scores: %d/%d = %.1f%n", passingSum, count, passingAverage);
     lowest = findMin(testScores);
     out.println("Minimum Test Score: " + lowest);
     out.println("Number of 'A' Test Scores: " + gradeA);
     in.close();
     out.close();
    }
    
    
    
    public static int findMin(int[] data)
    {
        int min = 100;
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] < min)
            {
                min = data[i];
            }
        }
        return min;
    }
    public static int countIf(int[] data, double low, double high)
    {
        int count = 0;
        for (int i = 0; i < data.length; i++)
        {
        if (data[i] <= high && data[i] >= low)
        {
            count++;
        }
        }
        return count;
    }
    public static int sumIf(int[] data, double low, double high)
    {
        int sum = 0;
        for (int i = 0; i < data.length; i++)
        {
        if (data[i] <= high && data[i] >= low)
        {
            sum += data[i];
        }
        }
        return sum;
        
    }
        
    public static String toString(int[] data)
    {
        String braces = "{";
        for (int element : data)
        {
            braces = braces + element + ", "; 
        }
        braces = braces.substring(0, braces.length()-2) + "}";
        return braces;
    }
}
