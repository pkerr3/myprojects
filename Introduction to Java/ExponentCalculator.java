/*
 * Calculates exponential functions without the use of any Java math methods
 * @author Phillip Kerr
 * @since October 26, 2017
 * Course CSC 1350 
 * PAWS ID: 89-046-5218
 * Project #2
 * Instructor: Wyatt Clements
 */
package exponentcalculator;
import java.util.Scanner;


public class ExponentCalculator {


    public static void main(String[] args) 
    {
     Scanner in = new Scanner(System.in);
     //declaring result
     double result = 1;
     //prompts user for input and takes input
      System.out.print("Enter the base and exponent, an integer, of a power ->");
      double b = in.nextDouble();
      int n = in.nextInt();
      //if the base is 0 and the exponent is negative
      if (b==0 && n<=0)
      {
          result = Double.NaN;
      }
      //if the base is 0 and the exponent is positive
      else if (b==0 && n>0)
      {
          result = 0;
      }
      //if the base is 1
      else if (b==1)
      {
          result = 1;
      }
      //if the base is -1 and the exponent is even
      else if (b==-1 && n%2==0)
      {
          result = 1;
      }
      //if the base is -1 and the exponent is odd
      else if (b==-1 && n%2!=0)
      {
          result = -1;
      }
      //if the exponent is 0
      else if (n==0)
      {
          result = 1;
      }
      //if the exponent is 1
      else if (n==1)
      {
          result = b;
      }
      //if the exponent is -1
      else if (n==-1)
      {
          result = 1/b;
      }
      //if the exponent is any number higher than 1 and b is any number besides -1, 1, or 0
      else if (n>1)
      {
          for(int i = 0; i < n; i++)
          {
          result = result*b;    
          }
      }
      //if the exponent is any number lower than -1 and b is any number besides -1, 1, or 0
      else if (n<-1)
      {
          for(int i = 0; i < -n; i++)
          {
          result = result*b;
          }
          result = 1/result;
      }
      //printing the math done by the program and the result    
      System.out.printf("%f^%d = %f%n", b, n, result);
     
    }
    
}
