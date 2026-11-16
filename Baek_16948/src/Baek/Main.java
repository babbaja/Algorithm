package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class night {
		int x;
		int y;
		int depth;
		
		public night(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	static int N;
	static int[] dx = new int[] {0, -2, -2, 0, 2, 2};
	static int[] dy = new int[] {-2, -1, 1, 2, 1, -1};
	static boolean[][] visited;
	static night start;
	static night end;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		int startx = Integer.parseInt(st.nextToken());
		int starty = Integer.parseInt(st.nextToken());
		int endx = Integer.parseInt(st.nextToken());
		int endy = Integer.parseInt(st.nextToken());
		
		start = new night(startx, starty, 0);
		end = new night(endx, endy, 0);
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<night> queue = new LinkedList<>();
		queue.offer(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			night curr = queue.poll();
			
			if (curr.x == end.x && curr.y == end.y) return curr.depth;
			
			for (int i = 0; i < 6; ++i) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				queue.offer(new night(nx, ny, curr.depth + 1));
			}
			
		}
		
		return -1;
	}
}
