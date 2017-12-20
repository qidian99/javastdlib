package hackerrank;
import java.util.*;
import java.io.*;


//Write your code here
class Calculator {
  public int power(int base, int exp) throws Exception {
      if(base >= 0 && exp >=0){
          return (int) Math.pow(base, exp);
      } else {
    	      //the return type of Math.pow is double
          throw new Exception("n and p should be non-negative");
      }
  }
}

public class Day17_MoreExceptions {
	 public static void main(String[] args) {
		    
	        Scanner in = new Scanner(System.in);
	        int t = in.nextInt();
	        //how to fetch input from console
	        while (t-- > 0) {
	            int n = in.nextInt();
	            int p = in.nextInt();
	            Calculator myCalculator = new Calculator();
	            try {
	                int ans = myCalculator.power(n, p);
	                System.out.println(ans);
	            }
	            catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        in.close();
	    }
	}

