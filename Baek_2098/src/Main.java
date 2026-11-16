import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		dp = new int[n][1 << n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 1));
	}
	
	static int dfs(int s, int visited) {
		if (visited == (1 << n) - 1) {
			if (arr[s][0] > 0) return arr[s][0];
			return Integer.MAX_VALUE;
		}
		
		if (dp[s][visited] != -1) return dp[s][visited]; 
		dp[s][visited] = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			if ((visited & (1 << i)) != 0 || arr[s][i] == 0) continue;
			
			int cost = dfs(i, visited | (1 << i));
			if (cost == Integer.MAX_VALUE) continue;
			dp[s][visited] = Math.min(dp[s][visited], cost + arr[s][i]);
		}
		return dp[s][visited];
	}
}
