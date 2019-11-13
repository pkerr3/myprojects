package primegeneratordemo;

import java.util.ArrayList;

/**
 * Provides abstract methods for classifying terms of 
 * an integer sequence, 1,2,...,n, as prime or composite
 * @author Duncan
 * @since 99-99-9999
 * <pre>
 * Note: DO NOTE MAKE ANY MODIFICATION TO THIS FILE
 * </pre>
 */
public interface PrimeGeneratorAPI 
{
    /**
     * Gives an array containing the sequence of prime numbers less than or equal to the
     * specified parameter
     * @param n the upper bound on the prime sequence
     * @return a sequence of prime numbers less than a specified integer in ascending order
     * @throws IllegalArgumentException when n is greater than the size of the current
     * integer sequence
     */
    ArrayList<Integer> generate(int n) throws IllegalArgumentException;
    
    /**
     * Gives the length of the integer sequence, 1...size, used to determine which integers
     * in [1,n] are prime or composite.
     * @return the length of the integer sequence
     */
    int size();
    
    /**
     * Determines whether n is a prime number.
     * @param n an integer
     * @return true if n is a prime number; otherwise, false
     */
    boolean isPrime(int n);
    
    /**
     * Gives the nth prime number
     * @param nth the index of a number in the prime sequence
     * @return the nth prime number
     * @throws IllegalArgumentException when nth > (size()/ln size())(1 + 0.992/ln size()) 
     */
    int getPrime(int nth) throws IllegalArgumentException;
    
    /**
     * Gives a string representation of a prime sequence in the format
     * [2, 3, ..., p], where p <= size()
     * @return the string representation of a prime sequence in the format
     * [2, 3, ..., p], where p <= size()
     */
    String toString();
    
    /**
     * Give the largest prime number in the sequence 1...size()
     * @return the largest prime number in a sequence of integers
     */
    int getMax();
}

