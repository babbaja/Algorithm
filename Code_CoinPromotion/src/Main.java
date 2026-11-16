import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[] cType = new char[n + 1];
		int[] coin = new int[n + 1];
		int[][] dp = new int[n + 1][k + 1];
		
		int INF = 987654321;
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], INF);
			dp[i][0] = 0;
		}
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			cType[i] = st.nextToken().charAt(0);
			coin[i] = Integer.parseInt(st.nextToken());			
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j < coin[i]) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				else {
					if (cType[i] == 'A') {
						dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coin[i]] + 1);
					}
					else {
						dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - coin[i]] + 1);
					}
				}
			}
		}
		
		System.out.println(dp[n][k] == INF ? -1 : dp[n][k]);
	}
}
