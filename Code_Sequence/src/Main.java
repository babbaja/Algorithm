import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static int[][] seq;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		seq = new int[m][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			seq[i][0] = Integer.parseInt(st.nextToken()) - 1;
			seq[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		visited = new boolean[n][n];
		visited[seq[0][0]][seq[0][1]] = true;
		select(1, seq[0][0], seq[0][1]);
		
		System.out.println(cnt);
	}
	
	static void select(int s, int x, int y) {
		if (s == m) {
			cnt += 1;
			return;
		}
//		System.out.println(s + " " + x + " " + y);
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (map[nx][ny] == 1 || visited[nx][ny]) continue;
			boolean check = false;
			if (nx == seq[s][0] && ny == seq[s][1]) {
				s += 1;
				check = true;
			}
			visited[nx][ny] = true;
			select(s, nx, ny);
			visited[nx][ny] = false;
			if (check) s -= 1;
		}
	}
}
