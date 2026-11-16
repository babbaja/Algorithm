package Magic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Golem {
	int x = -2;
	int y;
	int dir;
	
	public Golem(int y, int dir) {
		this.y = y;
		this.dir = dir;
	}
}

public class Main {
	static int R;
	static int C;
	static int K;
	static int[][] map;
	static Golem[] golems;
	static int fairyScore = 0;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[R][C];
			golems = new Golem[K];
			
			for (int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				golems[i] = new Golem(start - 1, dir);
			}
			
			for (int i = 0; i < K; ++i) {
				int fairy = 0;
//				if (map[0][golems[i].y] != 0) { //처음에 내려올때도 돌리면서 내려올 수 있으니까 
//					forestClear();
//				}
	
				moveGolem(golems[i]);
				if (golems[i].x <= 0) forestClear(); //여기 수정 
				else {
					golemParking(golems[i], i);
					fairy = fairyMove(golems[i]);
				}
//				System.out.println(golems[i].x + " " + golems[i].y + " " + golems[i].dir);

//				for (int x = 0; x < R; ++x) {
//					System.out.println();
//					for (int y = 0; y < C; ++y) {
//						System.out.printf("%3d", map[x][y]);
//					}
//				}
				
//				System.out.println("fairy : " + fairy);
				fairyScore += fairy;
			}
			System.out.println(fairyScore);
	}

		static void moveGolem(Golem golems) { //moving 조건 추가 
			boolean moving = false;
			//남 
			if (isMove(golems, 2)) {
				golems.x += dx[2];
				golems.y += dy[2];
				moveGolem(golems);
				moving = true;
			}
			//서 남 
			if (!moving && isMove(golems, 3)) {
				golems.x += dx[3];
		        golems.y += dy[3];
				if (isMove(golems, 2)) {
					golems.x += dx[2];
					golems.y += dy[2];
					golems.dir -= 1;
					if (golems.dir < 0) golems.dir = 3;
					moveGolem(golems);
					moving = true;
				}
				else {
					golems.x -= dx[3];
					golems.y -= dy[3];
				}
			}
			//동 남 
			if (!moving && isMove(golems, 1)) {
				golems.x += dx[1];
				golems.y += dy[1];
				if (isMove(golems, 2)) {
					golems.x += dx[2];
					golems.y += dy[2];
					golems.dir = (golems.dir + 1) % 4;
					moveGolem(golems);
					moving = true;
				}
				else {
					golems.x -= dx[1];
					golems.y -= dy[1];
				}
			}
		}
		
		static boolean isMove(Golem golem, int dir) {
		    int nx = golem.x + dx[dir];
		    int ny = golem.y + dy[dir];

		    for (int i = 0; i < 4; ++i) {
		        int nnx = nx + dx[i];
		        int nny = ny + dy[i];

		        if (nny < 0 || nnx >= R || nny >= C) return false;
		        if (nnx >= 0 && map[nnx][nny] != 0) return false;
		    }

		    return true;
		}

		
		static void golemParking(Golem golem, int num) {
			map[golem.x][golem.y] = num + 1;
			for (int i = 0; i < 4; ++i) {
				int nx = golem.x + dx[i];
				int ny = golem.y + dy[i];
				
				map[nx][ny] = num + 1;
			}
			int nx = golem.x + dx[golem.dir];
			int ny = golem.y + dy[golem.dir];
			map[nx][ny] = 0 - (num + 1);
		}
		
		
		static void forestClear() {
			for (int i = 0; i < R; ++i) {
				for (int j = 0; j < C; ++j) map[i][j] = 0;
			}
		}
		
		static int fairyMove(Golem golem) {
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {golem.x, golem.y});
			
			boolean[][] visited = new boolean[R][C];
			for (int i = 0; i < R; ++i) Arrays.fill(visited[i], false);
			visited[golem.x][golem.y] = true;
			int max = golem.x;
			
			
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				
				if (max < curr[0]) max = curr[0];
				
				for (int i = 0; i < 4; ++i) {
					int nx = curr[0] + dx[i];
					int ny = curr[1] + dy[i];
//					System.out.println("이동 : " + nx + " " + ny);
					
					if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
					
					if (visited[nx][ny] || map[nx][ny] == 0) continue;
					
					if (map[curr[0]][curr[1]] > 0) {
						if (Math.abs(map[nx][ny]) == map[curr[0]][curr[1]]) {
							visited[nx][ny] = true;
							queue.offer(new int[] {nx, ny});
						}
					}
					else {
						visited[nx][ny] = true;
						queue.offer(new int[] {nx, ny});
					}
				}
				
			}
			
			return max + 1;
		}
}
