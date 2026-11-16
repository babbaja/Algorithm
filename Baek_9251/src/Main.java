import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str1 = "0" + br.readLine();
		String str2 = "0" + br.readLine();
		
		char[] list1 = str1.toCharArray();
		char[] list2 = str2.toCharArray();
		int[][] dp = new int[list1.length + 1][list2.length + 1];
		
		for (int i = 1; i < list1.length; i++) {
			for (int j = 1; j < list2.length; j++) {
				if (list1[i] == list2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		System.out.println(dp[list1.length - 1][list2.length - 1]);
	}
}
