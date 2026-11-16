import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static int[][][] dp;
	static int[] dx = new int[] {0, 1, 1}; // →, ↘, ↓ 
	static int[] dy = new int[] {1, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		dp = new int[n][n][4];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(dfs(0, 1, 1));
	}
	
	static int dfs(int x, int y, int type) {
		if (x == n - 1 && y == n - 1) return 1;
		if (dp[x][y][type] != -1) {
			return dp[x][y][type];
		}
		dp[x][y][type] = 0;
		
		for (int i = 0; i < 3; i++) {
			if (type == 1 && i == 2) continue;
			else if (type == 3 && i == 0) continue;
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (i == 1 && (map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1)) continue;
			if (map[nx][ny] == 1) continue;
			
			dp[x][y][type] += dfs(nx, ny, i + 1);
		}
		return dp[x][y][type];
	}
}
