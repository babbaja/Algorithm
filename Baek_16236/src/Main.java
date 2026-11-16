import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static int sc;
	static int sr;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	static int level = 2;
	static int eat = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}
		
		System.out.println(solve());
	}
	
	static int solve() {
		int T = 0;
		while (true) {
			int time = bfs();
			if (time == -1) break;
			T += time;

			if (eat == level) {
				level++;
				eat = 0;
			}
		}
		
		return T;
	}
	
	static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[2] == o2[2]) { 
				if (o1[0] == o2[0]) return o1[1] - o2[1];
				else return o1[0] - o2[0];
			}
			return o1[2] - o2[2];
		});
		boolean[][] visited = new boolean[n][n];
		pq.offer(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			if (map[curr[0]][curr[1]] != 0 && map[curr[0]][curr[1]] < level) {
				sr = curr[0];
				sc = curr[1];
				eat++;
				map[sr][sc] = 0;
				return curr[2];
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if (level < map[nx][ny] || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				pq.offer(new int[] {nx, ny, curr[2] + 1});
			}
		}
		return -1;
	}
}
