import java.util.*;
public class AdvanceRecursionBacktracking {

    //friends pairing
    static int counter = 1;

    public static void solution(int i, int n, boolean[] used, String asf) {
      if(i > n ){
          System.out.println(counter+ "." + asf);
          counter++;
          return;
      }
      if(used[i] == true)
          solution(i+1,n,used,asf);
      else{
          used[i] = true;
          solution(i+1,n,used,asf+ "(" + i + ") ");  
          for(int j = i+1; j<=n; j++){
              if(used[j] == false){
              used[j] = true;
              solution(i+1,n,used,asf+ "(" + i +","+ j+") ");
              used[j] = false;}
          }
          used[i] = false;
      }
    }
     
    //k-partitions
    // static int counter = 0;
	public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
		//write your code here
		if(i > n){
		    if(rssf == k){
		        counter++;
		        System.out.print(counter + ". ");
		        for(ArrayList<Integer> ele : ans)
		          System.out.print(ele+" ");
		        System.out.println();    
		    }
		    return;
		}
		for(int j = 0; j <ans.size(); j++){
		    if(ans.get(j).size() >0){
		        ans.get(j).add(i);
		        solution(i+1,n,k,rssf,ans);
		        ans.get(j).remove(ans.get(j).size()-1);
		    }
		    else {
		        ans.get(j).add(i);
		        solution(i+1,n,k,rssf+1,ans);
		        ans.get(j).remove(ans.get(j).size()-1);
		        break;
		    }
		}
	}
    //tug of war
    static int mindiff = Integer.MAX_VALUE;
	static String ans = "";

	public static void solve(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1,int soset2) {
        if(vidx == arr.length){
            int sum = Math.abs(soset1 - soset2);
            if(sum < mindiff){
                mindiff = sum;
                ans = set1 + " "+set2;
            }
            return;
        }  
        if(set1.size() < (arr.length + 1)/2){
            set1.add(arr[vidx]);
            solve(arr,vidx+1,set1,set2,soset1+arr[vidx],soset2);
            set1.remove(set1.size()-1);
        }
        if(set2.size() < (arr.length + 1)/2) {
            set2.add(arr[vidx]);
            solve(arr,vidx+1,set1,set2,soset1,soset2+arr[vidx]);
            set2.remove(set2.size()-1);
        }
	}
    
}
