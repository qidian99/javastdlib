package hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Day11_2DArrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for(int i=0; i < 6; i++){
            for(int j=0; j < 6; j++){
                arr[i][j] = in.nextInt();
            }
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        //check rows
        for(int i = 0 ; i + 2 < arr.length; i++){
            //check columns
            for(int j = 0; j + 2 < arr[i].length; j ++ ){
               //calculate the sum of H shape
               sum += arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + 
                      arr[i + 1][j + 1] + arr[i + 2][j] + arr[i + 2][j + 1] +
                      arr[i + 2][j + 2];
               max = sum > max? sum: max;
               //reset sum
               sum = 0;
            }

        }
        System.out.print(max);
    }
}

/*
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0
19
 */

