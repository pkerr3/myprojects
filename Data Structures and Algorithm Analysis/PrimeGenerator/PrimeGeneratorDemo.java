package primegeneratordemo;
/** Generates Numbers and Tests if They Are Prime
 * @author Phillip Kerr
 * @since September 4, 2018
 * @see
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A test bed for the PrimeGenerator implementation.  
 * @author Duncan, YOUR NAME
 * @since 99-99-9999
 * @see PrimeGeneratorAPI, PrimeGenerator
 */

public class PrimeGeneratorDemo 
{
    public static void main(String[] args) 
    {
        Scanner cin = new Scanner(System.in);
        Random generator = new Random(System.currentTimeMillis());
        System.out.print("Enter a positive integer -> ");
        int n = cin.nextInt();
        PrimeGenerator primeSeq = new PrimeGenerator(n,'E');
        System.out.printf("Is %d a prime number? %b%n",n,primeSeq.isPrime(n));
        System.out.printf("Prime numbers in [1,%d] are %s.%n",n,primeSeq);
        System.out.printf("The largest prime number in [1,%d] is %d.%n",n,primeSeq.getMax());
        ArrayList<Integer> primes = primeSeq.generate(n);
        System.out.printf("The number of prime numbers in [1,%d] is %d.%n",n,primes.size());
        int randInt = generator.nextInt(primes.size());
        for(int i = 1;i<100;i--)
        {
            if(primeSeq.isPrime(randInt))
            {
                break;
            }
            else
            {
                randInt = generator.nextInt(n);
            }
        }
        System.out.printf("A random prime in [1, %d] is %d.%n", n, randInt);
        System.out.println();
        System.out.println("Empirical Analysis of Brute-force vs Sieve of Eratosthenes");
        System.out.println("Prime Sequence Generation Algorithms");
        System.out.printf("====================================================================================%n");
        System.out.printf("n         Eratosthenes(ns)   max prime in [1,n]       Naive(ns)  max Prime in [1,n]%n");
        System.out.printf("------------------------------------------------------------------------------------%n");

        for (int i = 400000; i <= 8000000; i += 400000)
        {
            long start = System.nanoTime();
            PrimeGenerator erat = new PrimeGenerator(i,'E');
            long elapsed = System.nanoTime() - start;
            long start2 = System.nanoTime();
            PrimeGenerator naive = new PrimeGenerator(i,'N');
            long elapsed2 = System.nanoTime() - start2;
            System.out.printf("%-17d %-17d %-17d %-17d %-17d. %n", i, elapsed, erat.getMax(), elapsed2, naive.getMax());
        }

    }
    
}