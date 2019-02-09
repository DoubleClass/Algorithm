public static boolean less(Comparable v,Comparable w){
		compTime++;
		return v.compareTo(w)<0;
	}

package compare;

public class Exercise_2_1_12 {
	public static void main(String[] args){
	double t1 = SortCompare.timeRandomInput("Shell",10000,1);
	}
}
//此题有些奇怪，并不觉得这个数是一个常数