import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //StringTokenizer st = new StringTokenizer(br.readLine());
      
      String str = "0" + br.readLine();
      String str2 = "0" + br.readLine();
      String str3 = "0" + br.readLine();
      
      char[] list = str.toCharArray();
      char[] list2 = str2.toCharArray();
      char[] list3 = str3.toCharArray();
      
      int len1 = list.length;
      int len2 = list2.length;
      int len3 = list3.length;
      
      int[][][] dp = new int[len1][len2][len3]; // str1 i번쩨 인덱스, str2 j번째, str3 k번째  인덱스에서 LCS 길이
      
      for (int i = 1; i < len1; i++) {
    	  for (int j = 1; j < len2; j++) {
    		  for (int k = 1; k < len3; k++) {
    			  if (list[i] == list2[j] && list[i] == list3[k]) {
    				  dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
    			  }
    			  else {
					  dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
    			  }
    		  }
    	  }
      }
      
      System.out.println(dp[len1 -1][len2 - 1][len3 - 1]);
  }
}