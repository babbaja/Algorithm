import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static List<int[]> virus;
	static List<Integer> wall;
	static int max = Integer.MIN_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		virus = new ArrayList<>();
		wall = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
			}
		}
		
		select(0, 0);
		System.out.println(max);
	}
	
	static void select(int s, int cnt) {
		if (cnt == 3) {
			spread();
			return;
		}
		
		for (int i = s; i < n * m; i++) {
			int x = i / m;
			int y = i % m;
			
			if (map[x][y] != 0) continue;
			wall.add(i);
			select(i + 1, cnt + 1);
			wall.remove(wall.size() - 1);
		}
	}
	
	static void spread() {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		for (int num : wall) {
			int x = num / m;
			int y = num % m;
			
			tmp[x][y] = 1;
		}
		
		Queue<int[]> q = new LinkedList<>();

	    for (int[] v : virus) {
	        q.offer(new int[]{v[0], v[1]});
	    }
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (tmp[nx][ny] != 0) continue;
				
				tmp[nx][ny] = 2;
				q.offer(new int[] {nx, ny});
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmp[i][j] == 0) cnt++;
			}
		}
		
		max = Math.max(max, cnt);
	}
}
