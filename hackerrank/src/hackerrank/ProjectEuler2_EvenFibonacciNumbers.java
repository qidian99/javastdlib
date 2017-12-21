package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProjectEuler2_EvenFibonacciNumbers {
    
    
    public static Queue<Integer> queue ;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            queue = new LinkedList<>();
            queue.add(2);
            queue.add(8);
            long n = in.nextLong();
            long sum = 0;
            while(true){
            	//when add is great than the maximum number of int (2 ^ 31 - 1), it yield a timeout
                int add = queue.remove();
                if(add > n){
                    break;
                } else {
                    sum += add;
                    queue.add( add + queue.peek() * 4);
                }
            }
            System.out.println(sum);
        }
    }
}

