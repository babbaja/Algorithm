import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static ArrayList<int[][]> stickers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		stickers = new ArrayList<>();
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[r][c];
			for (int x = 0; x < r; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < c; y++) {
					sticker[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			stickers.add(sticker);
		}
		
		for (int t = 0; t < k; t++) {
			attach(t);
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static void attach(int num) {
		int[][] sticker = stickers.get(num);	
		
		int sr = Integer.MAX_VALUE;
		int sc = Integer.MAX_VALUE;
		
		boolean check = false;
		for (int t = 0; t < 4; t++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					check = isAttach(i, j, sticker);
					if (check) {
						sr = i;
						sc = j;
						break;
					}
				}
				if (check) break;
			}
			if (check) break;
			sticker = rotate(sticker);
		}

		int r = sticker.length;
		int c = sticker[0].length;
		if (check) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (sticker[i][j] == 0) continue;
					map[sr + i][sc + j] = sticker[i][j];
				}
			}
		}
	}
	
	static boolean isAttach(int x, int y, int[][] sticker) {
		int r = sticker.length;
		int c = sticker[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (x + i >= n || y + j >= m || x + i < 0 || y + j < 0) return false;
				if (sticker[i][j] == 1 && map[x + i][y + j] == 1) return false;
			}
		}
		
		return true;
	}
	
	static int[][] rotate(int[][] sticker) {
		int r = sticker.length;
		int c = sticker[0].length;
		int[][] tmp = new int[c][r];
		
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				tmp[i][j] = sticker[r - 1 - j][i];
			}
		}
		return tmp;
	}
}
