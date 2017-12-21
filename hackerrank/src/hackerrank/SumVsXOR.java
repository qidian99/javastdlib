package hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SumVsXOR {

    static long solve(long n) {
        // Complete this function
        long sum = 0;
        int zeros = 0;
        while(n > 0){
            if((n & 1) == 0) zeros ++;
            n = n >> 1;
        }
        return 1L << zeros;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long result = solve(n);
        System.out.println(result);
    }
}

/* Sample Explanation

public class Solution {
public static void main(String [] args) {
    // Save input 
    Scanner scan = new Scanner(System.in);
    long n = scan.nextLong();
    scan.close();

    // Calculate number of "x" values that will satisfy n+x = n^x 
    int zeroes = numZeroes(n);
    long result = 1L << zeroes; // same as (long) Math.pow(2, zeroes);
    System.out.println(result);
}

private static int numZeroes(long n) {
    int count = 0;
    while (n > 0) {
        if ((n & 1) == 0) {
            count++;
        }
        n >>= 1; // divides by 2
    }
    return count;
}
}

*/