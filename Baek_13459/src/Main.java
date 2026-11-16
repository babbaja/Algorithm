import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		int rx = 0, ry = 0;
		int bx = 0, by = 0;
		
		for (int i = 0; i < n; i++) {
			char[] list = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = list[j];
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
					map[i][j] = ',';
				}
				else if (map[i][j] == 'B') {
					bx = i;
					by = j;
					map[i][j] = '.';
				}
			}
		}
		
		System.out.println(bfs(rx, ry, bx, by));
	}
	
	static int bfs(int rx, int ry, int bx, int by) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {rx, ry, bx, by, 0});
		boolean[][][][] visited = new boolean[n][m][n][m];
		visited[rx][ry][bx][by] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[4] > 10) return 0;

			for (int i = 0; i < 4; i++) {
				int[] red = move(cur[0], cur[1], i);
				int[] blue = move(cur[2], cur[3], i);
				
				if (map[blue[0]][blue[1]] == 'O') return 0;
				if (map[red[0]][red[1]] == 'O') return cur[4] + 1;
				
				if (red[0] == blue[0] && red[1] == blue[1]) {
					if (i == 0) {
						if (cur[0] > cur[2]) red[0] -= dx[i];
						else if (cur[0] < cur[2]) blue[0] -= dx[i];
					}
					else if (i == 1) {
						if (cur[1] > cur[3]) red[1] -= dy[i];
						else if (cur[1] < cur[3]) blue[1] -= dy[i];
					}
					else if (i == 2) {
						if (cur[0] > cur[2]) blue[0] -= dx[i];
						else if (cur[0] < cur[2]) red[0] -= dx[i];
					}
					else {
						if (cur[1] < cur[3]) red[1] -= dy[i];
						else if (cur[1] > cur[3]) blue[1] -= dy[i];
					}
				}
				
				if (visited[red[0]][red[1]][blue[0]][blue[1]]) continue;
				visited[red[0]][red[1]][blue[0]][blue[1]] = true;
				q.offer(new int[] {red[0], red[1], blue[0], blue[1], cur[4] + 1});
			}
			
		}
		return 0;
	}
	
	static int[] move(int nx, int ny, int i) {

		while (true) {
			nx += dx[i];
			ny += dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			if (map[nx][ny] == '#') break;
			if (map[nx][ny] == 'O') return new int[]{nx, ny};
		}
		
		return new int[]{nx, ny};
	}
}
