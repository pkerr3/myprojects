/**
 * Evaluates Polynomial Equations
 * CSC 1350 Project #3
 * @author Phillip Kerr
 * @since November 21, 2017
 */
package hornersmethod;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class HornersMethod {
/**
* Evaluates a polynomial using the specified value.
* @param coeffs an array of coefficients of a polynomial arranged in
* order of descending powers with its first element, the coefficients of
* the highest order term being non-zero except when the polynomial is 0.
* @param x the value to be used when evaluating the polynomial
* @return the value of the polynomial evaluated at the specified value
*/
public static double hornerEval(double[] coeffs, double x)
{
    double sum = coeffs[0];
    for(int i = 0; i<coeffs.length-1; i++)
    {
        if (coeffs.length>1)
        {
       sum = (sum*x)+(coeffs[i+1]);
        }
    }
    return sum;
}
/**
* Gives a string representation of this polynomial in the format
* c_{n}x^n + c_{n-1}x^{n-1} + c_{n-2}x^{n-2} + ... + c_1x + c_0
* For example, given the array [2, -3, 1, -4, 1] it generates the
* string 2x^4 - 3x^3 + x^2 - 4x + 1. The string generated should not
* include -1, 0 or 1 as coefficients of terms with exponent >= 1 and
* should only include 0 when the polynomial is the monomial 0.
* @param coeffs an array of coefficients of a polynomial arranged in
* order of descending powers with its first element, the coefficients of
* the highest order term being non-zero except when the polynomial is 0.
* @return a string representation of this polynomial
*/
public static String polyToString(double[] coeffs)
{
    String total = "";
    for (int i = 0; i<coeffs.length; i++)
    {
        if (i!=coeffs.length-1&&i!=coeffs.length-2)
        {
            if (i==0&&(coeffs[i]!=1.0&&coeffs[i]!=0.0&&coeffs[i]!=-1.0))
            {
              total += coeffs[i] + "x^" + ((coeffs.length-1)-i);  
            }
            else if (i==0&&(coeffs[i]==1.0||coeffs[i]==-1.0))
            {
                if (coeffs[i]==1.0)
                {
            total += "x^" + ((coeffs.length-1)-i);
                }
                else if (coeffs[i]==-1.0)
                {
                    total += "-x^" + ((coeffs.length-1)-i);
                }
            }
            else if (coeffs[i]!=1.0&&coeffs[i]!=0.0&&coeffs[i]!=-1.0)
            {
                if (coeffs[i]>0)
                {
            total += "+" + coeffs[i] + "x^" + ((coeffs.length-1)-i);
                }
                else if (coeffs[i]<0)
                {
                    total += "-" + Math.abs(coeffs[i]) + "x^" + ((coeffs.length-1)-i);
                }
            }
            else if (coeffs[i]==1.0||coeffs[i]==-1.0)
            {
                if (coeffs[i]==1.0)
                {
            total += "+x^" + ((coeffs.length-1)-i);
                }
                else if (coeffs[i]==-1.0)
                {
                    total += "-x^" + ((coeffs.length-1)-i);
                }
            }
        }
        else if (i==coeffs.length-2)
        {
            if (i==0&&(coeffs[i]!=1.0&& coeffs[i]!=0.0&&coeffs[i]!=-1.0))
            {
                total += coeffs[i] + "x";
            }
            else if (i==0&&(coeffs[i]==1.0||coeffs[i]==-1.0))
            {
                if(coeffs[i]==1.0)
                {
            total += "x";
                }
                else if (coeffs[i]==-1.0)
                {
                    total += "-x";
                }
            }
            else if (coeffs[i]!=1.0&&coeffs[i]!=0.0&&coeffs[i]!=-1.0)
            {
                if (coeffs[i]>0.0)
                {
            total += "+" + coeffs[i] + "x";
                }
                else if (coeffs[i]<0.0)
                {
                    total += "-" + Math.abs(coeffs[i]) + "x";
                }
            }
            else if (coeffs[i]==1.0||coeffs[i]==-1.0)
            {
                if(coeffs[i]==1.0)
                {
            total += "+x";
                }
                else if (coeffs[i]==-1.0)
                {
                    total += "-x";
                }
            }
        }
        else if (coeffs[coeffs.length-1]!=0.0)
        {
            if (i==0)
            {
                total += coeffs[i];
            }
            else if (coeffs[i] > 0)
            {
         total += "+" + coeffs[i];  
            }
            else if (coeffs[i] < 0)
            {
              total += "-" + Math.abs(coeffs[i]);
            }
        }
        else if (coeffs[0]==0&&coeffs.length==1)
        {
            total += coeffs[i];
        }
    }
    return total;
}

    public static void main(String[] args) throws IOException 
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the degree of the polynomial (deg p)->");
        int p = in.nextInt();
        double[] coeffs = new double[p+1];
        System.out.print("Enter coefficients of p(x) in descending powers->");
        for (int i = 0; i < coeffs.length; i++)
        {
            coeffs[i] = in.nextDouble();
        }
        System.out.print("Enter x to compute p(x)->");
        double x = in.nextInt();
        System.out.print("Enter the output file name->");
        PrintWriter out = new PrintWriter(in.next());
        System.out.println("p(x) = " + polyToString(coeffs));
        out.println("p(x) = " + polyToString(coeffs));
        System.out.println("p(" + x + ") = " + hornerEval(coeffs, x));
        out.println("p(" + x + ") = " + hornerEval(coeffs, x));
        out.close();
    }
    
}
