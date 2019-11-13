/* Arranges Grades in Ascending Order
 * CSC 1350 Lab #7
 * @author Phillip Kerr
 * @since November 1, 2017
 */
package grade;

import java.util.Arrays;


public class Grade {
    private static int bin0[] = new int[101];
     private static int bin1[] = new int[101];
     private static int bin2[] = new int[101];
     private static int bin3[] = new int[101];
     private static int bin4[] = new int[101];
     private static int bin5[] = new int[101];
     private static int bin6[] = new int[101];
     private static int bin7[] = new int[101];
     private static int bin8[] = new int[101];
     private static int bin9[] = new int[101];

    public static void main(String[] args) {
    int[] grades = {308, 80, 96, 75, 43, 304, 30, 90, 607, 114};
    System.out.println("Original Data: " + Arrays.toString(grades));
    
    moveToBins(grades, 1);
    int startPos = 0, pastNum;
     pastNum = bin0[0];
     emptyBin(bin0, grades, startPos);
     startPos += pastNum;
     pastNum = bin1[0];
     emptyBin(bin1, grades, startPos);
     startPos += pastNum;
     pastNum = bin2[0];
     emptyBin(bin2, grades, startPos);
     startPos += pastNum;
     pastNum = bin3[0];
     emptyBin(bin3, grades, startPos);
     startPos += pastNum;
     pastNum = bin4[0];
     emptyBin(bin4, grades, startPos);
     startPos += pastNum;
     pastNum = bin5[0];
     emptyBin(bin5, grades, startPos);
     startPos += pastNum;
     pastNum = bin6[0];
     emptyBin(bin6, grades, startPos);
     startPos += pastNum;
     pastNum = bin7[0];
     emptyBin(bin7, grades, startPos);
     startPos += pastNum;
     pastNum = bin8[0];
     emptyBin(bin8, grades, startPos);
     startPos += pastNum;
     pastNum = bin9[0];
     emptyBin(bin9, grades, startPos);
     startPos += pastNum;
     System.out.println("Data arranged by units digit: " + Arrays.toString(grades));
     
     startPos = 0;
     moveToBins(grades, 10);
     pastNum = bin0[0];
     emptyBin(bin0, grades, startPos);
     startPos += pastNum;
     pastNum = bin1[0];
     emptyBin(bin1, grades, startPos);
     startPos += pastNum;
     pastNum = bin2[0];
     emptyBin(bin2, grades, startPos);
     startPos += pastNum;
     pastNum = bin3[0];
     emptyBin(bin3, grades, startPos);
     startPos += pastNum;
     pastNum = bin4[0];
     emptyBin(bin4, grades, startPos);
     startPos += pastNum;
     pastNum = bin5[0];
     emptyBin(bin5, grades, startPos);
     startPos += pastNum;
     pastNum = bin6[0];
     emptyBin(bin6, grades, startPos);
     startPos += pastNum;
     pastNum = bin7[0];
     emptyBin(bin7, grades, startPos);
     startPos += pastNum;
     pastNum = bin8[0];
     emptyBin(bin8, grades, startPos);
     startPos += pastNum;
     pastNum = bin9[0];
     emptyBin(bin9, grades, startPos);
     startPos += pastNum;
     System.out.println("Data arranged by units ten: " + Arrays.toString(grades));
     
     startPos = 0;
     moveToBins(grades, 100);
     pastNum = bin0[0];
     emptyBin(bin0, grades, startPos);
     startPos += pastNum;
     pastNum = bin1[0];
     emptyBin(bin1, grades, startPos);
     startPos += pastNum;
     pastNum = bin2[0];
     emptyBin(bin2, grades, startPos);
     startPos += pastNum;
     pastNum = bin3[0];
     emptyBin(bin3, grades, startPos);
     startPos += pastNum;
     pastNum = bin4[0];
     emptyBin(bin4, grades, startPos);
     startPos += pastNum;
     pastNum = bin5[0];
     emptyBin(bin5, grades, startPos);
     startPos += pastNum;
     pastNum = bin6[0];
     emptyBin(bin6, grades, startPos);
     startPos += pastNum;
     pastNum = bin7[0];
     emptyBin(bin7, grades, startPos);
     startPos += pastNum;
     pastNum = bin8[0];
     emptyBin(bin8, grades, startPos);
     startPos += pastNum;
     pastNum = bin9[0];
     emptyBin(bin9, grades, startPos);
     startPos += pastNum;
     System.out.println("Final data: " + Arrays.toString(grades));
    }
/**
* Gives a reference to the bin whose numeric suffix
* corresponds to the specified number.
* @param binNum the number suffix of a bin
* @return a reference to the bin with the specified
* numeric suffix.
*/
public static int[] selectBin(int binNum)
{
         switch (binNum) {
             case 0:
                 return bin0;
             case 1:
                 return bin1;
             case 2:
                 return bin2;
             case 3:
                 return bin3;
             case 4:
                 return bin4;
             case 5:
                 return bin5;
             case 6:
                 return bin6;
             case 7:
                 return bin7;
             case 8:
                 return bin8;
             default:
                 return bin9;
         }
}
/**
* Scans the specified array from left to right, data[0] to
* data[n-1], where n is the length of the array, and places
* each element in the bin that corresponds to its digit in
* the specified place by first increasing the effective size
* of the array by 1 and then copying the element at the index
* equals to the new effective size of the bin. The effective
* size of a bin, the number of useful data elements that it
* contains, is stored as the first element of the bin. For
* example, when the data element is 98 and the place is 1,
* 98 is inserted in bin8; when the place is 10, it is inserted
* in bin9; when the place is 100, it is inserted in bin0.
* @param data an array of integers in the range 0...100
* @param place the place of the digit in the data that
* determines which bin it will be inserted in; place = 1
* (units) place, 10 (tens), 100 (hundreds) place etc. It
* must be a power of 10.
*/
public static void moveToBins(int[] data, int place)
{
    int digit;
    for (int i = 0; i < data.length; i++)
    {
        if (place == 1)
        {
             digit = data[i]%10;
        }
        else if (place == 10)
        {
            digit = (data[i]%100)/10;
        }
        else
        {
            digit = data[i]/100;
        }
        int effectiveSize = selectBin(digit)[0];
        selectBin(digit)[effectiveSize + 1] = data[i];
        selectBin(digit)[0] = effectiveSize + 1;

    }
}
/**
* Uses the contents of the specified bin to overwrite the contents of
* the data array, starting at the specified position in the data
* array. Also, sets the effective size, the first element of the bin
* array, to 0 after the elements of the data array are overwritten.
* @param bin a reference to the array whose elements will be used to
* overwrite the elements of the data array starting at index startPos
* in the data array.
* @param data the array whose elements are to be overwritten.
* @param startPos the index at which the overwriting of the contents of
* data begins. For example, if the effective size of bin is 4 and
* startPos is 0, then data[0] <- bin[1], data[1] <- bin[2],
* data[2] <- bin[3] and data[3] <- bin[4]. In general,
* data[startPos + i - 1] <- bin[i], i=1,...,binSize
*/
public static void emptyBin(int[] bin, int[] data, int startPos)
{
    
    for (int i = 0; i < bin[0]; i++)
    {
        data[startPos++] = bin[i+1];
    }
    bin[0] = 0;
}

    }
