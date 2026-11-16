package Hide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Runner {
	int x;
	int y;
	int dir;
	boolean alive = true;
	
	public Runner (int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

class Soolae {
	int x;
	int y;
	int dir = 0;
	int index = 0;
	int moveNum = 0;
	
	public Soolae(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N;
	static int M;
	static int H;
	static int K;
	static boolean[][] tree;
	static Runner[] run;
	static Soolae soolae;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static int[] soolaeMove;
	static int moveIndex = 1;
	static int totalScore;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		tree = new boolean[N][N];
		for (int i = 0; i < N; ++i) Arrays.fill(tree[i], true);
		
		run = new Runner[M];
		soolae = new Soolae(N / 2, N / 2);
		soolaeMove = new int[2 * N - 1];
		
		for (int i = 1; i < N; ++i) {
			soolaeMove[2 * i - 2] = i;
			soolaeMove[2 * i - 1] = i;
		}
		soolaeMove[2 * N - 2] = N - 1;
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			--x; --y;
			run[i] = new Runner(x, y, dir);
		}
		
		for (int i = 0; i < H; ++i) { // 나
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			--x; --y;
			tree[x][y] = false;
		}
		
		
		for (int i = 0; i < K; ++i) {
			move(i + 1);
		}
		System.out.println(totalScore);
		
	}
	
	
	static void move(int t) {
		//도망자
		for (int i = 0; i < M; ++i) {
			if (canRun(run[i]) && run[i].alive) {
				int nx = run[i].x + dx[run[i].dir];
				int ny = run[i].y + dy[run[i].dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					nx = run[i].x - dx[run[i].dir];
					ny = run[i].y - dy[run[i].dir];
					run[i].dir = (run[i].dir + 2) % 4;
					nx = run[i].x + dx[run[i].dir];
					ny = run[i].y + dy[run[i].dir];
				}
				
				if (soolae.x == nx && soolae.y == ny) continue;
				
				run[i].x = nx;
				run[i].y = ny;
			}
		}
		
//		for (int i = 0; i < M; ++i) {
//			System.out.println("도망 " + run[i].x + " " + run[i].y);
//		}
		//술랭
		int nx = soolae.x + dx[soolae.dir];
		int ny = soolae.y + dy[soolae.dir];
		++soolae.moveNum;
		if (soolae.moveNum == soolaeMove[soolae.index]) {
			soolae.moveNum = 0;
			soolae.index += moveIndex;
			if (moveIndex == 1) soolae.dir = (soolae.dir + 1) % 4;
			else soolae.dir -= 1;
			
			if (soolae.dir < 0) soolae.dir = 3;
		}
		//System.out.println(soolae.index + " " + soolae.moveNum + " " + moveIndex);
		if (soolae.index == -1 && soolae.moveNum == 0 && moveIndex == -1) {
			soolae.dir = 0;
			++soolae.index;
			moveIndex = 1;
		}
		else if (soolae.index == 2 * N - 1 && soolae.moveNum == 0 && moveIndex == 1) {
			//System.out.println("마지막");
			soolae.dir = 2;
			--soolae.index;
			moveIndex = -1;
		}
		soolae.x = nx;
		soolae.y = ny;
		
		//System.out.println(soolae.x +" " + soolae.y + " " + soolae.dir);
		
		//점수
		int cnt = 0;
		for (int i = 0; i < 3; ++i) {
			int chx = soolae.x + dx[soolae.dir] * i;
			int chy = soolae.y + dy[soolae.dir] * i;
			
			if (chx < 0 || chy < 0 || chx >= N || chy >= N) continue;
			
			if (tree[chx][chy]) {
				for (int j = 0; j < M; ++j) {
					if (run[j].x == chx && run[j].y == chy && run[j].alive) {
						run[j].alive = false;
						++cnt;
					}
				}
			}
		}
		totalScore += cnt * t;
	}
	
	static boolean canRun(Runner run) {
		int distance = Math.abs(soolae.x - run.x) + Math.abs(soolae.y - run.y);
		return distance <= 3 ? true : false;
	}

}
