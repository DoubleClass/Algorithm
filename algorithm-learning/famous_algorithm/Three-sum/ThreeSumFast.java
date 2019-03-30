import java.util.Arrays;
public class ThreeSumFast{
	public static int cal(int[]a,int target){
		Arrays.sort(a);
		int N=a.length;
		int cnt=0;
			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){
					if(BinarySearch.rank(target-a[i]-a[j],a)>j)
						cnt++;
				}
			}
		return cnt;
		}
		public static void main(String[] args){
			int[]a=new int[]{-1,2,-2,5,0};
			System.out.println(cal(a,0));
			
		}
	}
