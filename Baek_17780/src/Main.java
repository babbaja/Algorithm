import java.io.*;
import java.util.*;

public class Main {
	static class Chess {
		int x;
		int y;
		int d;
		
		public Chess(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static int n;
	static int k;
	static int[][] map;
	static int[] dx = new int[] {0, 0, 0, -1, 1};
	static int[] dy = new int[] {0, 1, -1, 0, 0};
	static ArrayList<Chess>[][] chess;
	static ArrayList<Chess> piece;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		chess = new ArrayList[n + 1][n + 1];
		piece = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				chess[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Chess c = new Chess(x, y, d);
			piece.add(c);
			chess[x][y].add(c);
		}
		
		System.out.println(start());
	}
	
	static int start() {
		int T = 0;
		
		while (T++ < 1000) {
			for (int t = 0; t < k; t++) {
				Chess curr = piece.get(t);
				int x = curr.x;
				int y = curr.y;
				int d = curr.d;
				if (chess[x][y].get(0) != curr) continue;
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 2) {
					if (d % 2 == 1) d += 1;
					else d -= 1;
					curr.d = d;
					nx = x + dx[d];
					ny = y + dy[d];
					if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 2) continue;
				}
				if (map[nx][ny] == 1) {
					int size = chess[x][y].size();
					for (int k = size - 1; k >= 0; k--) {
						chess[nx][ny].add(chess[x][y].get(k));
					}
				}
				else {
					chess[nx][ny].addAll(chess[x][y]);
				}
				for (Chess c : chess[x][y]) {
					c.x = nx;
					c.y = ny;
				}
				chess[x][y].clear();
				if (chess[nx][ny].size() >= 4) return T;
			}
		}
		return -1;
	}
}
