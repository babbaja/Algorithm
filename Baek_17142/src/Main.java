import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static ArrayList<int[]> virus;
	static ArrayList<int[]> onvirus;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0 ,1};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus = new ArrayList<>();
		onvirus = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
			}
		}
		
		select(0, 0);
		if (min ==  Integer.MAX_VALUE) System.out.println(-1);
		else if (min == Integer.MIN_VALUE) System.out.println(0);
		else System.out.println(min);
	}
	
	static void select(int s, int c) {
		if (c == m) {
			spread();
			return;
		}
		
		for (int i = s; i < virus.size(); i++) {
			onvirus.add(new int[] {virus.get(i)[0], virus.get(i)[1]});
			select(i + 1, c + 1);
			onvirus.remove(onvirus.size() - 1);
		}
	}
	
	static void spread() {
		int[][] tmp = new int[n][n];
		for (int i = 0; i < n; ++i) Arrays.fill(tmp[i], Integer.MAX_VALUE);
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				tmp[i][j] = map[i][j];
//			}
//		}
		
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> q = new LinkedList<>();
		for (int[] curr : onvirus) {
			q.offer(new int[] {curr[0], curr[1], 0});
			visited[curr[0]][curr[1]] = true;
		}
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + curr[0];
				int ny = curr[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if (map[nx][ny] == 1 || visited[nx][ny]) continue;
				tmp[nx][ny] = curr[2] + 1;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, curr[2] + 1});
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0) max = Math.max(max, tmp[i][j]);
			}
		}
		min = Math.min(min, max);
	}
}
