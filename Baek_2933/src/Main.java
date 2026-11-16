import java.io.*;
import java.util.*;

public class Main {
	static int r;
	static int c;
	static char[][] map;
	static int n;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int l = Integer.parseInt(st.nextToken());
			int d = i % 2;
			
			cluster(l , d);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void cluster(int l, int d) {
		int before = 1;
		int[][] area = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'x' && area[i][j] == 0) {
					dfs(i, j, area, before++);
				}
			}
		}
		
		int row = r - l;
		if (d == 0) {
			for (int i = 0; i < c; i++) {
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					break;
				}
			}
		}
		else {
			for (int i = c - 1; i >= 0; i--) {
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					break;
				}
			}
		}
		int after = 1;
		int[][] area2 = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'x' && area2[i][j] == 0) {
					dfs(i, j, area2, after++);
				}
			}
		}

		if (before == after) return;
		
		boolean[] check = new boolean[after];
		for (int i = 0; i < c; i++) {
			check[area2[r - 1][i]] = true;
		}
		
		int down = 0;
		for (int i = 1; i < after; i++) {
			if (!check[i]) down = i;
		}

		int num = r;
		for (int j = 0; j < c; j++) {
			int cnt = 0;
			for (int i = r - 1; i >= 0; i--) {
				if (area2[i][j] == down) {
					num = Math.min(num, cnt);
					continue;
				}
				cnt++;
				if (map[i][j] == 'x') cnt = 0;
			}
		}
	
		for (int j = 0; j < c; j++) {
			for (int i = r - 1; i >= 0; i--) {
				if (area2[i][j] == down) {
					map[i + num][j] = 'x';
					map[i][j] = '.';
				}
			}
		}
	}
	
	static void dfs(int x, int y, int[][] area, int num) {
		if (area[x][y] != 0) return;
		area[x][y] = num;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
			if (map[nx][ny] == '.') continue;
			
			dfs(nx, ny, area, num);
		}
	}
}
