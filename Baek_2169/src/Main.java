import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[n][m];
		dp[0][0] = map[0][0];
		for (int j = 1; j < m; j++) {
			dp[0][j] += dp[0][j - 1] + map[0][j];
		}
		
		for (int i = 1; i < n; i++) {
			int[] left = new int[n];
			int[] right = new int[n];
			
			left[0] = map[i][0] + dp[i - 1][0];
			for (int j = 1; j < m; j++) {
				left[j] = map[i][j] + Math.max(dp[i - 1][j], left[j - 1]);
			}
			right[m - 1] = map[i][m - 1] + dp[i - 1][m - 1];
			for (int j = m - 2; j >= 0; j--) {
				right[j] = map[i][j] + Math.max(dp[i - 1][j], right[j + 1]);
			}
			
			for (int j = 0; j < m; j++) {
				dp[i][j] = Math.max(left[j], right[j]);
			}
		}
		
		System.out.println(dp[n - 1][m - 1]);
	}
}
