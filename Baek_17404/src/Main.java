import java.io.*;
import java.util.*;

public class Main {
	static int INF = 1000001;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] rgb = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n + 1][3];
		int min = INF;
		for (int t = 0; t < 3; t++) {
			for (int i = 0; i < 3; i++) {
				if (t == i) dp[1][t] = rgb[1][t];
				else dp[1][t] = INF;
			}
			
			for (int i = 2; i <= n; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
			}
			
			for (int i = 0; i < 3; i++) {
				if (i != t) min = Math.min(min, dp[n][i]);
			}
		}
		
		System.out.println(min);
	}
}
