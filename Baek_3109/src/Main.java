import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1};
	static int cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				map[i] = st.nextToken().toCharArray();
			}
		}
		
		for (int i = 0; i < n; i++) {
			dfs(i, 0);
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int x, int y) {
		if (y == m - 1) {
			cnt += 1;
			return true;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + 1;
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			if (map[nx][ny] == 'x' || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			if (dfs(nx, ny)) return true;
		}
		return false;
	}
}
