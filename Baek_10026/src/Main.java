import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static char[][] map;
	static int[][] area;
	static int[][] warea;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		area = new int[n][n];
		int num = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (area[i][j] == 0) {
					area[i][j] = num;
					dfs(i, j, num++);
				}
			}
		}
		
		warea = new int[n][n];
		int wnum = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (warea[i][j] == 0) {
					warea[i][j] = wnum;
					wdfs(i, j, wnum++);
				}
			}
		}
		

		System.out.println((num - 1) + " " + (wnum - 1));
	}
	
	static void dfs(int x, int y, int num) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (map[x][y] != map[nx][ny] || area[nx][ny] != 0) continue;
			
			area[nx][ny] = num;
			dfs(nx, ny, num);
		}
	}
	
	static void wdfs(int x, int y, int num) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (warea[nx][ny] != 0) continue;
			if (map[x][y] == 'R' || map[x][y] == 'G') {
				if (map[nx][ny] == 'B') continue;
			}
			else if (map[x][y] == 'B') {
				if (map[nx][ny] == 'R' || map[nx][ny] == 'G') continue;
			}
			
			warea[nx][ny] = num;
			wdfs(nx, ny, num);
		}
	}
}
