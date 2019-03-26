package compare;


import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class Exercise_2_1_12 {
	 public static void main(String[] args) {
	        Comparable[] array = {2, 20, -1, -30, 30, 5, 6, 8, -99, -3, 0, 4, 4, 4};
	        Comparable[] arrays ={1,2,3,4,5,6};
	        //StdOut.println("Check: " + check(array,));
	        StdOut.println("Check: " + check(arrays,6));
	        StdOut.println("Expected: true");
	    }

	   
	    private static boolean check(Comparable[] array,int size) {
	    	Comparable[] hex = new Comparable[size];
	    	for(int i = 0; i< size;i++){
	    		hex[i] = array[i];
	    	}
	    	Arrays.sort(array);
	    	for(int i = 0; i< size; i++){
	    		if(hex[i]!=array[i])return false;
	    	}
	    	return true;
	    }

}
