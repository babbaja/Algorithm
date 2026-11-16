import java.io.*;
import java.util.*;

public class Main {
	static class Box {
		int c, w, h, k, r;
		
		public Box(int c, int w, int h, int k) {
			this.c = c;
			this.w = w;
			this.h = h;
			this.k = k;
		}
	}
	static Map<Integer, Box> boxes;
	static int n, m;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

        boxes = new HashMap<>();
        map = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k  = Integer.parseInt(st.nextToken());
            int h  = Integer.parseInt(st.nextToken());
            int w  = Integer.parseInt(st.nextToken());
            int c  = Integer.parseInt(st.nextToken());
            
            Box cur = new Box(c, w, h, k);
            boxes.put(k, cur);
            init(cur);
        }
        
        for (int t = 0; t < m / 2; t++) {
        	System.out.println(leftoff());
        	gravity();
        	

        	System.out.println(rightoff());
        	gravity();
        }
        
        if (m % 2 != 0) {
        	System.out.println(leftoff());
        }
	}
	
	static void gravity() {
		List<Box> move = new ArrayList<>();
		for (Box b : boxes.values()) {
			if (b.r != -1) move.add(b);
		}
		
		move.sort((o1, o2) -> {
			return o2.r - o1.r;
		});
		
		for (Box b : move) down(b);
	}
	
	static void down(Box cur) {
		int col = 0;
		for (int i = cur.r + cur.h; i <= n; i++) {
			boolean check = true;
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				if (map[i][j] != 0) {
					check = false;
					break;
				}
			}
			if (!check) break;
			col = i;
		}
		if (col == 0) return;
		
		for (int i = cur.r; i < cur.r + cur.h; i++) {
	        for (int j = cur.c; j < cur.c + cur.w; j++) {
	            map[i][j] = 0;
	        }
	    }
		
		cur.r = col - cur.h + 1;
		for (int i = cur.r; i < cur.r + cur.h; i++) {
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				map[i][j] = cur.k;
			}
		}
	}
	
	static int rightoff() {
		int num = 101;
		for (Box b : boxes.values()) {
			if (b.r == -1 || num < b.k) continue;
			boolean check = true;
			
			for (int i = b.r; i < b.r + b.h; i++) {
				for (int j = b.c + b.w; j <= n; j++) {
					if (map[i][j] != 0) {
						check = false;
						break;
					}
				}
				if (!check) break;
			}
			if (check) {
				num = Math.min(num, b.k);
			}
		}
		if (num == 101) return -1;
		
		Box cur = boxes.get(num);
		for (int i = cur.r; i < cur.r + cur.h; i++) {
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				map[i][j] = 0;
			}
		}
		cur.r = -1;
		return num;
	}
	
	static int leftoff() {
		int num = 101;
		for (Box b : boxes.values()) {
			if (b.r == -1 || num < b.k) continue;
			boolean check = true;
			
			for (int i = b.r; i < b.r + b.h; i++) {
				for (int j = b.c - 1; j > 0; j--) {
					if (map[i][j] != 0) {
						check = false;
						break;
					}
				}
				if (!check) break;
			}
			if (check) {
				num = Math.min(num, b.k);
			}
		}
		if (num == 101) return -1;
		
		Box cur = boxes.get(num);
		for (int i = cur.r; i < cur.r + cur.h; i++) {
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				map[i][j] = 0;
			}
		}
		cur.r = -1;
		return num;
	}
	
	static void init(Box cur) {
		int col = 0;
		for (int i = cur.h; i <= n; i++) {
			boolean check = true;
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				if (map[i][j] != 0) {
					check = false;
					break;
				}
			}
			if (!check) break;
			col = i;
		}
		
		cur.r = col - cur.h + 1;
		for (int i = cur.r; i < cur.r + cur.h; i++) {
			for (int j = cur.c; j < cur.c + cur.w; j++) {
				map[i][j] = cur.k;
			}
		}
	}
}
