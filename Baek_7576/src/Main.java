import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) q.offer(new int[]{i, j});
			}
		}
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				if (map[nx][ny] != 0) continue;
				
				q.offer(new int[] {nx, ny});
				map[nx][ny] = map[cur[0]][cur[1]] + 1;
			}
		}

		int day = 0;
		boolean isall = true;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				day = Math.max(day, map[i][j]);
				if (map[i][j] == 0) {
					isall = false;
					break;
				}
			}
			if (!isall) break;
		}
		if (isall) System.out.println(day - 1);
		else System.out.println(-1);
	}
}
