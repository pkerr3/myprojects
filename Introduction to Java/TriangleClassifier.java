/*
 * Classifies Whether or Not Three Values Can Create a Triangle, Whether or not the Triangle as Acute, Right, or Obtuse and Keeps Track of Number of Obtuse Triangles
 * @author Phillip Kerr
 * @since October 4, 2017
 * Course CSC 1350
 * Paws ID: 89-046-5218
 * Lab #4
 */
package triangleclassifier;

import java.util.Scanner;

public class TriangleClassifier {


    public static void main(String[] args) 
    {
        //declaring variables, strings, and the boolean
      int side1 = 0, side2 = 0, side3 = 0, max = 0, SumofSquares = 0, numObtuse = 0, numTriangle = 0;
      String boolExpr = null, classification = null;
      boolean valid;
      Scanner in = new Scanner(System.in);
      //for statement determining the number of times the program asks for a triangle
      for (int i = 0; i < 10; ++i)
      {
       valid = false;
       //while statement prompting user input and prints if the three values entered cannot create a triangle
       while (!valid)
       {
          System.out.print("Please input three integers -> ");
          if (in.hasNextInt())
              side1 = in.nextInt();
          if (in.hasNextInt())
              side2 = in.nextInt();
          if (in.hasNextInt())
          {
              side3 = in.nextInt();
              if ((side1 <= 0 || (side2 <= 0) || (side3 <= 0)))
              {
                  System.out.println("Is not a triangle: all sides must have a length greater than zero.");
                  i++;
              }
              else if ((side1+side2 <= side3)|| (side1+side3 <= side2) || (side2+side3 <= side1))
              {
                  System.out.println("Is not a triangle: the sum of two sides must not be less than or equal to the length of the third side.");
                  i++;
              }
              else
                  valid = true;
          }
          else
          {
              System.out.println("You must input three whole numbers.");
              in.nextLine();
          }     
          //if statement used for finding the max and the sum of the squares of the two values that are not the max; also defines a boolean expression which will later be used to show the equation to the user
          if ((side1 > side2)&&(side1 > side3))
          {
              max = side1;
              SumofSquares = (side2*side2)+(side3*side3);
              boolExpr = side2 + "^2 " + "+ " + side3 + "^2";
          }
          else if ((side2 > side1)&&(side2 > side3))
          {
              max = side2;
              SumofSquares = (side1*side1)+(side3*side3);
              boolExpr = side1 + "^2 " + "+ " + side3 + "^2";
          }
          else 
          {
              max = side3;
              SumofSquares = (side1*side1)+(side2*side2);
              boolExpr = side1 + "^2 " + "+ " + side2 + "^2";
          }
          //if statement used to clasify the triangle; checks if the digits inputted actually resulted in a triangle, and then increments 1 to the number of triangles
          if (max*max < SumofSquares)
          {
              classification = "acute: " + max + "^2 " + "< " + boolExpr;
              if (valid)
              {
              numTriangle++;
              }
          }
          else if (max*max > SumofSquares)
          {
              classification = "obtuse: " + max + "^2 " + "> " + boolExpr;
              if (valid)
              {
              numTriangle++;
              numObtuse++;
              }
          }
          else
          {
              classification = "right: " + max + "^2 " + "= " + boolExpr;
              if (valid)
              {
              numTriangle++;
              }
          }
          }
          //print statement that informs user of the triangle's sides, classification,and equation used to come to the conclusion of classification
          {
              if (valid)
              {
           System.out.printf("A triangle with sides %d, %d and %d is %s.%n", side1, side2, side3, classification);
              }
       }
       }
      //print statement which displys the fraction of triangles which were obtuse
      System.out.println(numObtuse + " of the " + numTriangle + " triangles is/are obtuse.");
       }
       }

      
    

          
                     
          
      
          
          
          
          
      
      
    
    

