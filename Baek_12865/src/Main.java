import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] weight = new int[n + 1];
		int[] back = new int[n + 1];
		int[][] dp = new int[n + 1][k + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			back[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j < weight[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + back[i]);
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
