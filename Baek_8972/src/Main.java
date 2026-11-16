import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static int[] jong = new int[2];
	static Queue<int[]> crazy;
	static int[] dx = new int[] {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dy = new int[] {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static int[] command;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		crazy = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			char[] list = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = list[j];
				if (map[i][j] == 'I') {
					jong[0] = i;
					jong[1] = j;
				}
				else if (map[i][j] == 'R') {
					map[i][j] = '.';
					crazy.offer(new int[] {i, j});
				}
			}
		}
		
		char[] list = br.readLine().toCharArray();
		command = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			command[i] = list[i] - '0';
		}
		
		int turn = game();
		
		for (int[] tmp : crazy) {
			map[tmp[0]][tmp[1]] = 'R';
		}
		
		StringBuilder sb = new StringBuilder();
		if (turn == -1) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		else {
			sb.append("kraj ").append(turn);
		}
		System.out.println(sb);
	}
	
	static int game() {
		int T = 0;
		
		while (T < command.length) {
			map[jong[0]][jong[1]] = '.';
			int nx = jong[0] + dx[command[T]];
			int ny = jong[1] + dy[command[T]];
			
			map[nx][ny] = 'I';
			jong[0] = nx;
			jong[1] = ny;
			
			int[][] arr = new int[n][m];

//			System.out.println(T);
			while (!crazy.isEmpty()) {
				int[] tmp = crazy.poll();
//				System.out.println(tmp[0] + " " + tmp[1]);
				if (map[tmp[0]][tmp[1]] == 'I') return T + 1;
				
				int idx = move(tmp);
				tmp[0] += dx[idx];
				tmp[1] += dy[idx];

//				System.out.println(tmp[0] + " " + tmp[1]);
				if (jong[0] == tmp[0] && jong[1] == tmp[1]) return T + 1;
				
				arr[tmp[0]][tmp[1]]++;
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] != 1) continue;
					crazy.offer(new int[] {i, j});
				}
			}
			
			
			T++;
		}
		
		return -1;
	}
	
	static int move(int[] curr) {
		int min = Integer.MAX_VALUE;
		int mIdx = 0;
		
		for (int i = 1; i <= 9; i++) {
			int nx = curr[0] + dx[i];
			int ny = curr[1] + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			
			int dist = Math.abs(jong[0] - nx) + Math.abs(jong[1] - ny);
			if (min > dist) {
				min = dist;
				mIdx = i;
			}
		}
//		System.out.println(min + " -- " + mIdx);
		return mIdx;
	}
}
