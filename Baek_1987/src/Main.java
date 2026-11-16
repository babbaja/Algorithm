import java.io.*;
import java.util.*;

public class Main {
	static int r;
	static int c;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	static char[][] map;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		visited = new boolean[26];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int depth) {
		max = Math.max(max, depth);
			
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
			int next = map[nx][ny] - 'A';
			if (visited[next]) continue;
			visited[next] = true;
			dfs(nx, ny, depth + 1);
			visited[next] = false;
		}
	}
}
