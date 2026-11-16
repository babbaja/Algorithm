import java.io.*;
import java.util.*;

public class Main {
	static class Shark {
		int x, y, num, d;
		boolean dead = false;
		int[][] direction = new int[5][4];
		
		public Shark (int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static int n, m, k;
	static int[][] map;
	static int[][] smell;
	static int[][] time;
	static Shark[] sk;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		smell = new int[n][n];
		time = new int[n][n];
		sk = new Shark[m + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) sk[map[i][j]] = new Shark(i, j, map[i][j]);
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			sk[i].d = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sk[i].direction[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		for (int i = 1; i <= m; i++) {
			smell[sk[i].x][sk[i].y]= sk[i].num;
			time[sk[i].x][sk[i].y]= k;
		}
		System.out.println(solve());
	}
	
	static int solve() {
		int t = 0;
		int deadS = 0;
		while (t++ < 1000) {
			for (int s = 1; s <= m; s++) {
				if (sk[s].dead) continue;
				move(sk[s]);
				if (sk[s].dead) deadS++;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (time[i][j] == 0) continue;
					time[i][j] -= 1;
					if (time[i][j] == 0) smell[i][j] = 0;
				}
			}
			
			for (int i = 1; i <= m; i++) {
				if (sk[i].dead) continue;
				smell[sk[i].x][sk[i].y]= sk[i].num;
				time[sk[i].x][sk[i].y]= k;
			}
			if (deadS == m - 1) return t;
		}
		
		return -1;
	}
	
	static void move(Shark s) {
		int[] cur = s.direction[s.d];
		
		boolean isMove = false;
		for (int i = 0; i < 4; i++) {
			int nx = s.x + dx[cur[i]];
			int ny = s.y + dy[cur[i]];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (smell[nx][ny] != 0) continue;
			
			map[s.x][s.y] = 0; 
			if (map[nx][ny] < s.num && map[nx][ny] != 0) {
				s.dead = true;
			}
			else {
				s.x = nx;
				s.y = ny;
				s.d = cur[i];
				map[nx][ny] = s.num;
			}
			isMove = true;
			break;
		}

		if (isMove) return;
		
		for (int i = 0; i < 4; i++) {
			int nx = s.x + dx[cur[i]];
			int ny = s.y + dy[cur[i]];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (smell[nx][ny] != s.num) continue;
			
			map[s.x][s.y] = 0; 
			if (map[nx][ny] < s.num && map[nx][ny] != 0) {
				s.dead = true;
			}
			else {
				s.x = nx;
				s.y = ny;
				s.d = cur[i];
				map[nx][ny] = s.num;
			}
			isMove = true;
			break;
		}
	}
}
