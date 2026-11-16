import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int x;
		int y;
		int num;
		
		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static int n;
	static int[][] map;
	static int[][] visited;
	static ArrayList<ArrayList<Node>> arr;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new int[n][n];
		arr = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int island = 1;
		arr.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && visited[i][j] == 0) {
					arr.add(new ArrayList<>());
					dfs(i, j, island++);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < island; i++) {
			for (Node n1 : arr.get(i)) {
				int x1 = n1.x;
				int y1 = n1.y;
				for (int j = i + 1; j < island; j++) {
					for (Node n2 : arr.get(j)) {
						int x2 = n2.x;
						int y2 = n2.y;
						min = Math.min(min, Math.abs(x1 - x2) + Math.abs(y1 - y2));
					}
				}
			}
		}
		
		System.out.println(min - 1);
	}
	
	static void dfs(int x, int y, int num) {
		visited[x][y] = num;
		arr.get(num).add(new Node(x, y, num));
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (map[nx][ny] == 0 || visited[nx][ny] != 0) continue;
			
			dfs(nx, ny, num);
		}
	}
}