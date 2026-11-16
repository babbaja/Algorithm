import java.io.*;
import java.util.*;

public class Main {
	static int r;
	static int c;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		visited = new boolean[r][c];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				map[r - 1 - j][i] = 1;
			}
		}
		int cnt = 0;
		for (int i = r - 1; i >= 0; i--) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					cnt += rain(i, j);
				}
			}
		}
		System.out.println(cnt);
	}
	
	static int rain(int x, int y) {
		visited[x][y] = true;
		int ly = y;
		while (map[x][ly] == 0) {
			ly -= 1;
			if (ly < 0) break;
			visited[x][ly] = true;
		}
		int ry = y;
		while (map[x][ry] == 0) {
			ry += 1;
			if (ry >= c) break;
			visited[x][ry] = true;
		}
		if (ly < 0 || ry >= c) return 0;
		return ry - ly - 1;
	}
}
