import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;
	static List<int[]> boom;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int cnt = 0;
		while (true) {
			visited = new boolean[12][6];
			boom = new LinkedList<>();
			boolean isBoom = false;
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						dfs(i, j, map[i][j]);
					}
					if (boom.size() >= 4) {
						for (int[] tmp : boom) map[tmp[0]][tmp[1]] = '.';
						isBoom = true;
					}
					boom.clear();
				}
			}
			
			if (!isBoom) break;
			cnt++;
			blockdown();
		}
		System.out.println(cnt);
	}
	
	static void blockdown() {
		Queue<Character> q = new LinkedList<>();
		
		for (int j = 0; j < 6; j++) {
			for (int i = 11; i >= 0; i--) {
				if (map[i][j] != '.') q.offer(map[i][j]);
			}
			
			for (int i = 11; i >= 0; i--) {
				if (!q.isEmpty()) map[i][j] = q.poll();
				else map[i][j] = '.';
			}
		}
	}
	
	static void dfs(int x, int y, char ch) {
		visited[x][y] = true;
		boom.add(new int[] {x, y});
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
			if (ch != map[nx][ny] || visited[nx][ny]) continue;
			
			dfs(nx, ny, ch);
		}
	}
}
