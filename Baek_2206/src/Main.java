import java.io.*;
import java.util.*;

public class Main {
	static int n; 
	static int m;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs(0, 0));
		
	}
	
	static int bfs(int x, int y) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[3] - o2[3];
		});
		boolean[][][] visited = new boolean[n][m][2];
		visited[x][y][0] = true;
		pq.offer(new int[] {0, 0, 0, 1});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (cur[0] == n - 1 && cur[1] == m - 1) return cur[3];
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny][cur[2]]) continue;
				
				if (map[nx][ny] == '1') {
					if (cur[2] == 1) continue;
					pq.offer(new int[] {nx, ny, 1, cur[3] + 1});
					visited[nx][ny][1] = true;
				}
				else {
					pq.offer(new int[] {nx, ny, cur[2], cur[3] + 1});
					visited[nx][ny][cur[2]] = true;
				}
			}
		}
		
		return -1;
	}
}
