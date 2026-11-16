package time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Monster {
	int x;
	int y;
	int dir;
	int time;
	
	public Monster(int x, int y, int dir, int time) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.time = time;
	}
}

public class Main {
	static int N;
	static int M;
	static int F;
	static int[][] map;
	static int[][][] wall;
	static Monster[] mon;	
	static int[] start = new int[3];
	static int[] dx = new int[] {0, 0, 1, -1};
	static int[] dy = new int[] {1, -1, 0, 0};
	static boolean[][] mapVisited;
	static boolean[][][] wallVisited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		mapVisited = new boolean[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		wall = new int[5][M][M];
		wallVisited = new boolean[5][M][M];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < M; ++j) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; ++k) {
					wall[i][j][k] = Integer.parseInt(st.nextToken());
					if (wall[i][j][k] == 2) {
						start[0] = i + 1;
						start[1] = j;
						start[2] = k;
					}
				}
			}
		}
		
		mon = new Monster[F];
		for (int i = 0; i < F; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			mon[i] = new Monster(r, c, dir, time);
			map[r][c] = -1;
		}
		
		System.out.println(escape());
		
	}

	static int escape() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {start[0], start[1] , start[2]});
		wallVisited[start[0]][start[1]][start[2]] = true;
		
		int time = 0;
		while (!queue.isEmpty()) {
			time += 1;
			monsterMove(time);
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; ++i) {
				int nx = cur[1] + dx[i];
				int ny = cur[2] + dy[i];

				int[] tmp;
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					tmp = wallMove(nx, ny, cur[0]);
					nx = tmp[1];
					ny = tmp[2];
					cur[0] = tmp[0];
				}
				if (cur[0] != 0) {
					if (wall[cur[0]][nx][ny] != 0 || wallVisited[cur[0]][nx][ny]) continue;
				}
				else {
					
				}
				
			}
		}
		return time;
	}
	
	static void monsterMove(int time) {
		for (int i = 0; i < F; ++i) {
			if (time % mon[i].time == 0) {
				int nx = mon[i].x + dx[mon[i].dir];
				int ny = mon[i].y + dy[mon[i].dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (map[nx][ny] != 0) continue;
				
				map[nx][ny] = -1;
				mon[i].x = nx;
				mon[i].y = ny;
			}
		}
	}
	
	static int[] wallMove(int x, int y, int num) {
		int[] next = new int[3];
		int check = -1;
		if (x < 0) {
			next[1] = M - 1;
			next[2] = y;
			check = 3;
		}
		else if (y < 0) {
			next[1] = x;
			next[2] = M - 1;
			check = 1;
		}
		else if (x >= N) {
			next[1] = 0;
			next[2] = y;
			check = 2;
		}
		else if (y >= N) {
			next[1] = x;
			next[2] = 0;
			check = 0;
		}
		if (num == 5) {
			if (check == 0) {
				next[0] = 1;
			}
			else if (check == 1) {
				next[0] = 2;
			}
			else if (check == 2) {
				next[0] = 3;
			}
			else if (check == 3) {
				next[0] = 4;
			}
		}
		else if (num == 4) {
			if (check == 0) {
				next[0] = 2;
			}
			else if (check == 1) {
				next[0] = 1;
			}
			else if (check == 2) {
				next[0] = 0;
			}
			else if (check == 3) {
				next[0] = 5;
			}
		}
		else if (num == 3) {
			if (check == 0) {
				next[0] = 1;
			}
			else if (check == 1) {
				next[0] = 2;
			}
			else if (check == 2) {
				next[0] = 0;
			}
			else if (check == 3) {
				next[0] = 5;
			}
		}
		else if (num == 2) {
			if (check == 0) {
				next[0] = 3;
			}
			else if (check == 1) {
				next[0] = 4;
			}
			else if (check == 2) {
				next[0] = 0;
			}
			else if (check == 3) {
				next[0] = 5;
			}
		}
		else if (num == 1) {
			if (check == 0) {
				next[0] = 4;
			}
			else if (check == 1) {
				next[0] = 3;
			}
			else if (check == 2) {
				next[0] = 0;
			}
			else if (check == 3) {
				next[0] = 5;
			}
		}
		return next;
	}
}

