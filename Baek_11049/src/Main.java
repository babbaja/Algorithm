import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[][] metrix = new int[n][2];
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			metrix[i][0] = Integer.parseInt(st.nextToken());
			metrix[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int len = 1; len < n - 1; len++) {
			for (int i = 0; i < n - len; i++) {
				int j = i + len;
				dp[i][j] = Integer.MAX_VALUE;
				
				for (int k = 1; k < j; k++) {
					int cost = dp[i][k] + dp[k + 1][j] + metrix[i][0] * metrix[k][1] * metrix[j][1];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}
		
		System.out.println(dp[0][n - 1]);
	}
}
