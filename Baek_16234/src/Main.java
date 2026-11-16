import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int x;
		int y;
		int v;
		
		public Node(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}
	static Node[][] map;
	static boolean[][] visited;
	static int n;
	static int l;
	static int r;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		int T = 0;
		map = new Node[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int v = Integer.parseInt(st.nextToken());
				map[i][j] = new Node(i, j, v);
			}
		}
		
		while (true) {
			
			boolean isMove = true;
			int cnt = 0;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						isMove = bfs(i, j);
					}
					if (isMove) cnt++;
				}
			}

			if (cnt == 0) break;
			T++;
		}
		
		System.out.println(T);
	}
	
	static boolean bfs(int x, int y) {
		int sum = 0;
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		ArrayList<Node> list = new ArrayList<>();
		q.offer(map[x][y]);
		list.add(map[x][y]);
		visited[x][y] = true;
		sum += map[x][y].v;
		
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
				int differ = Math.abs(tmp.v - map[nx][ny].v);
				if (differ < l || differ > r) continue;
				
				visited[nx][ny] = true;
				sum += map[nx][ny].v;
				cnt += 1;
				q.offer(map[nx][ny]);
				list.add(map[nx][ny]);
			}
		}
		
		if (cnt == 1) return false;
		else {
			int age = sum / cnt;
			for (Node node : list) {
				node.v = age;
			}
			return true;
		}
	}
}
