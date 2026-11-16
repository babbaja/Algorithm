import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n;
	static int l;
	static int[][] map;
	static int[][] command;
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static ArrayList<Node> snake;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		snake = new ArrayList<>();
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
		
		l = Integer.parseInt(br.readLine());
		command = new int[l][2];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			int d = 0;
			if (dir.equals("L")) d = -1;
			else if (dir.equals("D")) d = 1;
			
			command[i][0] = t;
			command[i][1] = d;
		}
		snake.add(new Node(1, 1));
		System.out.println(start());
	}
	
	static int start() {
		int T = 0;
		int direction = 0;
		int idx = 0;
		
		int cx = 1;
		int cy = 1;
		while (true) {
			T++;
			int nx = dx[direction] + cx;
			int ny = dy[direction] + cy;
			
			if (nx < 1 || ny < 1 || nx > n || ny > n) break;
			boolean check = false;
			for (Node tmp : snake) {
				if (tmp.x == nx && tmp.y == ny) {
					check = true;
					break;
				}
			}
			if (check) break;
			
			snake.add(0, new Node(nx, ny));
			if (map[nx][ny] == 1) {
				map[nx][ny] = 0;
			}
			else {
				snake.remove(snake.size() - 1);
			}
			cx = nx;
			cy = ny;

			if (idx < l && command[idx][0] == T) {
				direction += command[idx][1];
				if (direction == 4) direction = 0;
				else if (direction < 0) direction = 3;
				idx++;
			}
		}
		
		return T;
	}
}
