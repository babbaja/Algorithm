import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int n;
	static int wr, wc, wd;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		wr = Integer.parseInt(st.nextToken());
		wc = Integer.parseInt(st.nextToken());
		wd = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		visited = new boolean[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) visited[i][j] = true;
			}
		}
		visited[wr][wc] = true;
		sb.append(wr).append(" ").append(wc).append(" ").append("\n");

		while (true) {
			move();
			if (!find()) break;
		}
		System.out.println(sb);
	}
	
	static boolean find() {
		// 1. 목표 지점 찾기 (기존 작성하신 훌륭한 우선순위 큐 로직 활용)
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[2] == o2[2]) {
				if (o1[0] == o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
			return o1[2] - o2[2];
		});
		pq.offer(new int[] {wr, wc, 0});
		
		boolean[][] fvisited = new boolean[n + 1][n + 1];
		fvisited[wr][wc] = true;
		
		int[] mx = new int[] {0, 1, 0, -1}; 
		int[] my = new int[] {-1, 0, 1, 0};
		
		int targetX = -1;
		int targetY = -1;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0];
			int y = cur[1];
			
			if (map[x][y] == 0 && !visited[x][y]) {
				targetX = x;
				targetY = y;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + mx[i];
				int ny = y + my[i];
				
				if (nx < 1 || ny < 1 || nx > n || ny > n ) continue;
				if (fvisited[nx][ny] || map[nx][ny] == 1) continue;
				
				fvisited[nx][ny] = true;
				pq.offer(new int[] {nx, ny, cur[2] + 1}); 
			}
		}
		if (targetX == -1) return false;
		
		int[][] dist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) Arrays.fill(dist[i], -1);
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {targetX, targetY});
		dist[targetX][targetY] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + mx[i];
				int ny = y + my[i];
				if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 1) continue;
				
				if (dist[nx][ny] == -1) {
					dist[nx][ny] = dist[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		int cx = wr;
		int cy = wc;
		int lastDir = -1;
		
		while (cx != targetX || cy != targetY) {
			for (int i = 0; i < 4; i++) {
				int nx = cx + mx[i];
				int ny = cy + my[i];
				
				if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 1) continue;
				
				if (dist[nx][ny] == dist[cx][cy] - 1) {
					cx = nx;
					cy = ny;
					lastDir = i;
					break; 
				}
			}
		}
		
		visited[targetX][targetY] = true;
		wr = targetX;
		wc = targetY;
		
		if (lastDir == 0) wd = 3;
		else if (lastDir == 1) wd = 2;
		else if (lastDir == 2) wd = 4;
		else if (lastDir == 3) wd = 1;
		
		sb.append(wr).append(" ").append(wc).append(" ").append("\n");
		return true;
	}
	
	static void move() {
		int[] mx = new int[] {-1, 0, 1, 0};
		int[] my = new int[] {0, 1, 0, -1};
		while (true) {
//			System.out.println(wr + " --- " + wc + " --- " + wd);
			int d = wd;
			if (wd == 1) d = 0;
			else if (wd == 4) d = 1;
			if (moveSea(mx[d], my[d])) {
				sb.append(wr).append(" ").append(wc).append(" ").append("\n");
				continue;
			}
			
			d = d - 1;
			if (d < 0) d = 3;
			if (moveSea(mx[d], my[d])) {
				sb.append(wr).append(" ").append(wc).append(" ").append("\n");
				if (wd == 1) wd = 3;
				else if (wd == 2) wd = 4;
				else if (wd == 3) wd = 2;
				else wd = 1;
				continue;
			}
			
			d = (d + 2) % 4;
			if (moveSea(mx[d], my[d])) {
				sb.append(wr).append(" ").append(wc).append(" ").append("\n");
				if (wd == 1) wd = 4;
				else if (wd == 2) wd = 3;
				else if (wd == 3) wd = 1;
				else wd = 2;
				continue;
			}
			
			d = (d + 1) % 4;
			if (moveSea(mx[d], my[d])) {
				sb.append(wr).append(" ").append(wc).append(" ").append("\n");
				if (wd == 1) wd = 2;
				else if (wd == 2) wd = 1;
				else if (wd == 3) wd = 4;
				else wd = 3;
				continue;
			}
			
			break;
		}
	}
	
	static boolean moveSea(int mx, int my) {
		int nx = wr + mx;
		int ny = wc + my;
//		System.out.println(nx + " " + ny);
		if (nx < 1 || ny < 1 || nx > n || ny > n) return false;
		if (visited[nx][ny] || map[nx][ny] == 1) return false;
		
		wr = nx;
		wc = ny;
		visited[nx][ny] = true;
		return true;
	}
}
