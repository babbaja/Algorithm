import java.io.*;
import java.util.*;

public class Main {
	static int r;
	static int c;
	static char[][] map;
	static int[] sequence;
	static int[][] area;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r + 1][c];
		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		int k = Integer.parseInt(br.readLine());
		sequence = new int[k];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			sequence[i] = r - Integer.parseInt(st.nextToken()) + 1;
			cluster(sequence[i], i);
		}
		
	}
	
	static void cluster(int line, int type) {
		boolean isBoom = false;
		area = new int[r + 1][c];
		int n1 = 1;
		for (int i = 1; i <= r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '.' && area[i][j] == 0) {
					dfs(i, j, n1++);
				}
			}
		}
		
		if (type % 2 == 0) {
			for (int i = 0; i < c; i++) {
				if (map[line][i] == 'x') {
					isBoom = true;
					map[line][i] = '.';
					break;
				}
			}
		}
		else {
			for (int i = c - 1; i >= 0; i--) {
				if (map[line][i] == 'x') {
					isBoom = true;
					map[line][i] = '.';
					break;
				}
			}
		}
		
		if (!isBoom) return;

		area = new int[r + 1][c];
		int n2 = 1;
		for (int i = 1; i <= r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '.' && area[i][j] == 0) {
					dfs(i, j, n2++);
				}
			}
		}
		
		if (n1 == n2) return;
		
		int down = 0;
		boolean isLast = false;
		for (int i = 1; i < n2; i++) {
			isLast = false;
			for (int j = 0; j < c; j++) {
				if (area[r][j] == i) {
					isLast = true;
				}
			}
			if (!isLast) down = i;
		}
		if (down == 0) return;
		
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < c; j++) {
			for (int i = r; i > 0; i--) {
				if (area[i][j] == down) {
					int drop = 0;
					for (int k = i + 1; k <= r; k++) {
						if (map[k][j] == 'x' && area[k][j] != down) break;
						drop++;
					}
					min = Math.min(min, drop);
					break;
				}
			}
		}
		
		for (int i = 1; i <= r; i++) {
			for (int j = 0; j < c; j++) {
				if 
			}
		}
	}
	
	static void dfs(int x, int y, int num) {
		area[x][y] = num;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
			if (map[nx][ny] == '.' || area[nx][ny] != num) continue;
			
			area[nx][ny] = num;
			dfs(nx, ny, num);
		}
	}
}
