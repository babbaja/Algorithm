import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int r, c, dr, dc;
		
		public Node(int r, int c, int dr, int dc) {
			this.r = r;
			this.c = c;
			this.dr = dr;
			this.dc = dc;
		}
	}
	static int n, m, power;
	static int[][] map;
	static int x, y;
	static List<Node> passenger;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		power = Integer.parseInt(st.nextToken());
		map = new int[n  + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		passenger = new LinkedList<>();
		passenger.add(null); passenger.add(null);
		int idx = 2;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dr = Integer.parseInt(st.nextToken());
			int dc= Integer.parseInt(st.nextToken());
			passenger.add(new Node(r, c, dr, dc));
			map[r][c] = idx++;
		}
		
		boolean possible = true;
		for (int i = 0; i < m; i++) {
			if (!taxi()) {
				possible = false;
				break;
			}
		}
		
		System.out.println(possible ? power : -1);
	}
	
	static boolean taxi() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[2] == o2[2]) {
				if (o1[0] == o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
			return o1[2] - o2[2];
		});
		boolean[][] visited = new boolean[n + 1][n + 1];
		visited[x][y] = true;
		pq.offer(new int[] {x, y, 0});
		
		int num = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (power < cur[2]) return false;
			if (map[cur[0]][cur[1]] > 1) {
				x = cur[0];
				y = cur[1];
				power -= cur[2];
				num = map[cur[0]][cur[1]];
				map[cur[0]][cur[1]] = 0;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
				if (map[nx][ny] == 1 || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				pq.offer(new int[] {nx, ny, cur[2] + 1});
			}
		}
		if (num == 0) return false;
		int dr = passenger.get(num).dr;
		int dc = passenger.get(num).dc;
		
		pq.clear();
		pq.offer(new int[] {x, y, 0});
		visited = new boolean[n + 1][n + 1];
		visited[x][y] = true;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (power < cur[2]) return false;
			if (cur[0] == dr && cur[1] == dc) {
				power += cur[2];
				x = cur[0];
				y = cur[1];
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
				if (map[nx][ny] == 1 || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				pq.offer(new int[] {nx, ny, cur[2] + 1});
			}
		}
		
		
		return false;
	}
}
