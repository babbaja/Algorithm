import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int d;
	static int[][] arr;
	static int[] arrow;
	static int[] dx = new int[] {0, -1, 0};
	static int[] dy = new int[] {-1, 0, 1};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		arrow = new int[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select(0, 0);
		System.out.println(max);
	}
	
	static void select(int s, int cnt) {
		if (cnt == 3) {
			start();
			return;
		}
		for (int i = s; i < m; i++) {
			arrow[i] = 1;
			select(i + 1, cnt + 1);
			arrow[i] = 0;
		}
	}
	
	static void start() {
		int cnt = 0;
		int[][] arr2 = new int[n + 1][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr2[i][j] = arr[i][j];
			}
		}

		int range = n - 1 - d;
		if (range < 0) range = 0;
		for (int t = 0; t < n; t++) {
			
			int[][] tmp = new int[n][m];
			for (int k = 0; k < m; k++) {
				if (arrow[k] == 0) continue;
				int[] kill = bfs(arr2, k);
				if (kill == null) continue;

				if (tmp[kill[0]][kill[1]] != 1) {
					cnt++;
					tmp[kill[0]][kill[1]] = 1;
				}
			}
			
			// 이동 
			for (int i = n - 1; i > 0; i--) {
				for (int j = 0; j < m; j++) {
					if (tmp[i - 1][j] == 1) arr2[i - 1][j] = 0;
					arr2[i][j] = arr2[i - 1][j];
				}
			}
			for (int j = 0; j < m; j++) {
				arr2[0][j] = 0;
			}
		}
		max = Math.max(cnt, max);
	}
	
	static int[] bfs(int[][] arr2, int k) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {n - 1, k, 1});
		boolean[][] visited = new boolean[n][m];
		visited[n - 1][k] = true;
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			if (curr[2] <= d && arr2[curr[0]][curr[1]] == 1) return curr;
			
			if (curr[2] >= d) continue;	
			for (int i = 0; i < 3; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, curr[2] + 1});
			}
		}
		return null;
	}
}
