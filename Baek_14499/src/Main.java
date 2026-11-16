import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static int[] dice = new int[7];
	static int[] dx = new int[] {0, 0, 0, -1, 1};
	static int[] dy = new int[] {0, 1, -1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < k; j++) {
			int turn = Integer.parseInt(st.nextToken());
			
			int nx = x + dx[turn];
			int ny = y + dy[turn];
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			
			roll(turn);
			
			if (map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			}
			else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}
			x = nx;
			y = ny;
			System.out.println(dice[1]);
		}
	}
	
	static void roll(int turn) {
		int tmp = dice[1];
		if (turn == 1) {
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
		}
		else if (turn == 2) {
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
		}
		else if (turn == 3) {
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
		}
		else {
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
		}
	}
}
