/*
 * Finds the Day of the Week of a Date and If the Date occurs in a Leap Year
 * @author Phillip Kerr
 * @since September 30, 2017
 * Course CSC 1350 
 * PAWS ID: 89-046-5218
 * Project #1
 * Instructor: Wyatt Clements
 */
package weekdayfinder;
import java.util.Scanner;

public class WeekdayFinder 
    {
    public static void main(String[] args)         
    { 
    Scanner in = new Scanner(System.in);
    //getting user input
    System.out.print("Enter numeric values for month, day and year of a date-->");
    int month = in.nextInt();
    int day = in.nextInt();
    int year = in.nextInt();
    //deciding if leap year or not
    boolean Leapyear;
    if (year%100 == 0)
    {
       Leapyear = false;
    }
    else if (year%400 == 0)
    {
        Leapyear = true;
    }
    else if (year%4 == 0)
    {
        Leapyear = true;
    }
    else
        Leapyear = false;
    //invalid inputs
    if ((year < 1583) || (month < 1) || (month > 12) || (day > 31) || (month == 3 && day == 31) || (month == 6 && day == 31) || (month == 9 && day == 31) || (month == 11 && day == 31) || (month == 2 && Leapyear == true && day > 29) || (month == 2 && Leapyear == false && day > 28))
    {
        System.out.printf("%02d/%02d/%d is not a valid date.", month, day, year);
    }
    //determining the day of the week
    int century = year/100;
    int u = 2*(3-(century%4));
    int v = year%100;
    int w = v/4;
    int m = 284;
    if (Leapyear == false && month == 1)
            {
            m = 0;
            }
    else if (Leapyear == true && month == 1)
            {
            m = 6;
            }
    else if (Leapyear == false && month == 2)
            {
            m = 3;    
            }
    else if (Leapyear == true && month == 2)
            {
            m = 2;    
            }
    else if (month == 3)
            {
            m = 3;
            }
    else if (month == 4)
            {
            m = 6;
            }
    else if (month == 5)
            {
            m = 1;
            }
    else if (month == 6)
            {
            m = 4;
            }
    else if (month == 7)
            {
            m = 6;    
            }
    else if (month == 8)
            {
            m = 2;
            }
    else if (month == 9)
            {
            m = 5;
            }
    else if (month == 10)
            {
            m = 0;   
            }
    else if (month == 11)
            {
            m = 3;
            }
    else if (month == 12)
            {
            m = 5;    
            }
    int y = u + v + w + m + day;
    int DayOfWeek = y%7;
    //does not print the day of the week if the date is invalid, otherwise prints the actual name of the day of the week
    if ((year < 1583) || (month < 1) || (month > 12) || (day > 31) || (month == 3 && day == 31) || (month == 6 && day == 31) || (month == 9 && day == 31) || (month == 11 && day == 31) || (month == 2 && Leapyear == true && day > 29) || (month == 2 && Leapyear == false && day > 28));
    else if (DayOfWeek == 0)
        System.out.print("Sunday, ");
    else if (DayOfWeek == 1)
        System.out.print("Monday, ");
    else if (DayOfWeek == 2)
        System.out.print("Tuesday, ");
    else if (DayOfWeek == 3)
        System.out.print("Wednesday, ");
    else if (DayOfWeek == 4)
        System.out.print("Thursday, ");
    else if (DayOfWeek == 5)
        System.out.print("Friday, ");
    else if (DayOfWeek == 6)
        System.out.print("Saturday, ");
    //does not print the date if it is invalid, otherwise prints the date itself and if it is or is not a leap year
    if ((year < 1583) || (month < 1) || (month > 12) || (day > 31) || (month == 3 && day == 31) || (month == 6 && day == 31) || (month == 9 && day == 31) || (month == 11 && day == 31) || (month == 2 && Leapyear == true && day > 29) || (month == 2 && Leapyear == false && day > 28));
    else if (Leapyear == true)
    System.out.printf("%02d/%02d/%d occurs in a leap year.", month, day, year);
    else
    System.out.printf("%02d/%02d/%d does not occur in a leap year.", month, day, year);
    }
}
