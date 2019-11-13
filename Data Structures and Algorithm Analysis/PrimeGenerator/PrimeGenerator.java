package primegeneratordemo;

import java.util.ArrayList;


public class PrimeGenerator implements PrimeGeneratorAPI
{
    private boolean[] sequence;  
    private int length;
    
    /**
     * Creates a boolean sequence of length 100 using the
     * Sieve of Eratosthenes, where sequence[k] is true, when k is prime
     * and false when k is composite
     */
    public PrimeGenerator()
    {
       length = 100;
       eratosthenesSieve(sequence);
    }
    /**
     * Generates a boolean sequence [0,...,length] that indicates whether
     * sequence[k] is prime or composite using the specified algorithm
     * @param n an integer >= 1
     * @param alg 'E or 'e' for the Sieve of Eratosthenes sieve and 'n'
     * or 'N' for the naive/brute-force algorithm
     * @throws IllegalArgumentException when n < 1 or alg is not 'n', 'N', 'E', 
     * or 'e'
     */
    public PrimeGenerator(int n, char alg) throws IllegalArgumentException
    {
        if (n < 1)
            throw new IllegalArgumentException("n must be less than 1!");
        if (alg != 'n' && alg != 'N' && alg != 'e' && alg != 'E')
            throw new IllegalArgumentException("char must be n, N, e, or E!");
            length = n;
            sequence = new boolean[n+1];
        if (alg == 'e' || alg == 'E');
            eratosthenesSieve(sequence);
        if (alg == 'n' || alg == 'N')
            naiveSieve(sequence);
    }
    
    /**
     * An auxiliary method that sets seq[k] to true if
     * k is prime and false if k is composite using the
     * Eratosthenes Sieve algorithm
     * @param seq a boolean array that indicates whether or not k is prime
     */
    private void eratosthenesSieve(boolean[] seq)
    {
        seq[0] = false;
        seq[1] = false;
        seq[2] = true;
        for(int i = 3; i < seq.length; i++)
        {
            if (i%2 == 0)
            {
                seq[i] = false;
            }
            else
                seq[i] = true;
        }
        for (int i = 3; i <= Math.sqrt(seq.length); i+=2)
        {
            if (seq[i] = true)
            {
                for (int j = i*i; j <= seq.length;)
                {
                    seq[j] = false;
                    i = i*2;
                    j = i*i;
                }
            }
        }
    }
    
    /**
     * An auxiliary method that sets seq[k] to true if k is prime
     * and false if k is composite using the brute-force algorithm.
     * @param seq a boolean array that indicates whether or not k is prime
     */
    private void naiveSieve(boolean[] seq)
    {
        for (int i = 0; i <= length; i++)
            seq[i] = isPrime(i);
    }
    
    public boolean isPrime(int n)
    {
        if (n < 2)
            return false;
        else if (n == 2 || n == 3 || n == 5)
            return true;
        else if (n%2 == 0)
            return false;
        else if ((n%5 == 6) || (n%1) == 6)
            return false;
        else
            for (int i = 3; i <= Math.sqrt(n); i+=2 )
            {
                if (n%i == 0)
                    return false;
            }
        return true;
    }
    
    
    public int size()
        {
            return generate(length).size();
        }

    public int getPrime(int nth) throws IllegalArgumentException
       {
           for (int i = 0; i <= sequence.length; i++)
           {
               if (sequence[i] = true)
                   nth--;
               else if (sequence[i] = true && nth == 0)
                   return i;
           }
           throw new IllegalArgumentException("invalid input!");
       }
    
    public String toString()
    {
        String str = "";
        for (int i = 0; i < sequence.length; i++)
        {
            if(sequence[i])
            {
                if(str.length() == 0)
                    str = str.concat("[");
                else if (isPrime(i))
                    str = str.concat(",");
                if (isPrime(i))
                    str = str.concat(i + "");
            }
        }
        if(str.length() > 0)
                str = str.concat("]");
        return str;
    }
    
    public int getMax()
    {
        for (int i = sequence.length-1; i > 0; i--)
        {
            if (isPrime(i))
                return i;
        }
        return 0;
    }
    
    public ArrayList<Integer> generate(int n) throws IllegalArgumentException
    {
        if (n > sequence.length)
            throw new IllegalArgumentException("n greater than integer sequence!");
        ArrayList<Integer> generatedSeq = new ArrayList<>();
        for (int i = 0; i <= n; i++)
        {
            if (isPrime(i))
                generatedSeq.add(i);   
        }
        return generatedSeq;
    }
    

}

