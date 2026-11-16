import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] mem = new int[n + 1];
		int[] cost = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) mem[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) cost[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n + 1][10001];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= 10000; j++) { // cost가 0인 경우도 있으니 반복은 0부터 
				if (j < cost[i]) {
					dp[i][j] = dp[i - 1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + mem[i]);
				}
			}
		}
		
		int ans = 0;
		for (int j = 0; j <= 10000; j++) {
			if (dp[n][j] >= m) {
				ans = j;
				break;
			}
		}
		System.out.println(ans);
	}
}
