/*
 * Calculates Minimum and Maximum of Test Scores, Prints the Amount of Failed Tests, the Scores on the Failed Test, and the Sum of the Scores of the Passed Tests
 * @author Phillip Kerr
 * @since October 11, 2017
 */
package minmaxfinder;
import java.util.Arrays;
import java.util.Scanner;

public class MinMaxFinder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //declaring array and variables
        int[] values = new int[250];
        int numTestScores = 0, lowest = 100, highest = 0, SumOfPass = 0, nonPass = 0;
        String nonPassesList = "";
        //prompts user for number of total tests
        System.out.print("Enter number of test scores (>=5) to compute the lowest, highest, and the sum of the passing scores -> ");
        numTestScores = in.nextInt();
        System.out.println();
        // prompts user for first test score
System.out.print("Enter the first test score [0-100] -> ");
        //calculates where the first test score will place among the others
        values[0] = in.nextInt();
        if (values[0] < lowest)
        {
            lowest = values[0];
        }
        
        if (values[0] > highest)
        {
            highest = values[0];
        }
        //defines if the score will pass or fail and what to do for each outcome
        if (values[0] > 69)
            SumOfPass = SumOfPass + values[0];
        else
        {   
            nonPass++;
            nonPassesList = nonPassesList + values[0] + " ";
        }
        //prints the necessary information
        System.out.println("Lowest = " + lowest + ", " + "Highest = " + highest + ", and Sum of Passing Scores = " + SumOfPass);
        if (values[0] <= 69)
        {
        System.out.println("Number of non-passing scores (" + nonPass + ")" + " : " + nonPassesList);
        System.out.println();
        }
        else
        {
            System.out.println();
        }
        //tells program to loop until the number of testscores is reached
        for (int i = 1; i < numTestScores; i++)
        {
        //prompts user for test scores
        System.out.print("Enter the next test score [0-100] -> ");
        values[i] = in.nextInt();
        //decides where score will place among the others
        if (values[i] < lowest)
        {
            lowest = values[i];
        }
        
        if (values[i] > highest)
        {
            highest = values[i];
        }
        //defines if the score will pass or fail and what to do for each outcome
        if (values[i] > 69)
        {
            SumOfPass = SumOfPass + values[i];
        }
        else
        {   
            nonPass++;
            nonPassesList = nonPassesList + values[i] + " ";
        }
        //prints the necessary information
        System.out.println("Lowest = " + lowest + ", " + "Highest = " + highest + ", and Sum of Passing Scores = " + SumOfPass);
        if (values[i] <= 69)
        {
        System.out.println("Number of non-passing scores (" + nonPass + ")" + " : " + nonPassesList);
        System.out.println();
        }
        else
        {
        System.out.println();
        }
        }
    }
}
       
        
    
    
    

