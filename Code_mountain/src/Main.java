import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int k;
	static int[][] map;
	static int[][][] dp;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		dp = new int[n][n][k];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				min = Math.min(min, dfs(i, j, 1));
			}
		}
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static int dfs(int x, int y, int cnt) {
		if (cnt == k) return 0;
		if (dp[x][y][cnt] != -1) return dp[x][y][cnt];
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (map[x][y] >= map[nx][ny]) continue;
			
			int cur = map[nx][ny] - map[x][y];
			int diff = dfs(nx, ny, cnt + 1);
			if (diff != Integer.MAX_VALUE) {
				int cost = Math.max(cur, diff);
				min = Math.min(min, cost);
			}
		}
		return dp[x][y][cnt] = min;
	}
}
