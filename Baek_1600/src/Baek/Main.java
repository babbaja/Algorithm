import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Monkey {
		int x;
		int y;
		int distance;
		int horse;
		
		public Monkey(int x, int y, int distance, int horse) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.horse = horse;
		}
	}
	static int[][] map;
	static boolean[][][] visited;
	static int W;
	static int H;
	static int[] dx = new int[] {-2, -2, -1, 1, 2, 2, 1, -1, 0, -1, 0, 1};
	static int[] dy = new int[] {-1, 1, 2, 2, 1, -1, -2, -2, -1, 0, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = "123";
		char[] list = str.toCharArray();
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Monkey mon = new Monkey(0, 0, 0, K);
		
		System.out.println(bfs(mon));
	}

	static int bfs(Monkey mon) {
		Queue<Monkey> queue = new LinkedList<>();
		queue.offer(mon);
		visited[0][0][mon.horse] = true;
		
		while (!queue.isEmpty()) {
			Monkey tmp = queue.poll();
					
			for (int i = 0; i < 12; ++i) {
				//System.out.println(tmp.x + " " + tmp.y + " " + tmp.distance +  " "+ tmp.horse);
				if (i < 8 && tmp.horse == 0) continue;
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				int move = tmp.distance + 1;
				int horsenum = tmp.horse;
				
				if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
				if (visited[nx][ny][horsenum] || map[nx][ny] == 1) continue;
				
				if (nx == H - 1 && ny == W - 1) return move;
				if (i < 8) horsenum = tmp.horse - 1;
				visited[nx][ny][horsenum] = true;

				
				//System.out.println("-----"+nx + " " + ny + " " + move +  " "+ horsenum);
				queue.offer(new Monkey(nx, ny, move, horsenum));
			}
		}
		
		return -1;
	}
}
