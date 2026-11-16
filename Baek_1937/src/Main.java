import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;
	static int[][] dp;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}

		System.out.println(max);
	}
	
	static int dfs(int x, int y) {
		if (dp[x][y] != 0) return dp[x][y];
		dp[x][y] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (arr[x][y] >= arr[nx][ny]) continue;
			
			dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
		}
		return dp[x][y];
	}
}
