import java.io.*;
import java.util.*;

public class Main {
	static class Dust {
		int x, y, num;
		
		public Dust(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static int[] dx = new int[] {0, -1, 0, 1};
	static int[] dy = new int[] {-1, 0, 1, 0};
	static int n;
	static int k;
	static int l;
	static int[][] map;
	static ArrayList<Dust> dusts;
	static ArrayList<Dust> cleaner;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		dusts = new ArrayList<>();
		cleaner = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					dusts.add(new Dust(i, j, map[i][j]));
				}
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			cleaner.add(new Dust(x, y, -1));
		}
		
		StringBuilder sb = new StringBuilder();
		while (l-- > 0) {
			move();
			clean();
			dirt();
			
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] > 0) sum += map[i][j];
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
	
	static void move() {
		boolean[][] cleaners = new boolean[n][n];
        for (Dust c : cleaner) {
            cleaners[c.x][c.y] = true;
        }
        for (Dust c : cleaner) {
        	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
    			if (o1[2] == o2[2]) {
    				if (o1[0] == o2[0]) return o1[1] - o2[1];
    				return o1[0] - o2[0];
    			}
    			return o1[2] - o2[2];
    		});
        	boolean[][] visited = new boolean[n][n];
        	visited[c.x][c.y] = true;
        	pq.offer(new int[] {c.x, c.y, 0});
        	while (!pq.isEmpty()) {
        		int[] cur = pq.poll();
        		
        		if (map[cur[0]][cur[1]] > 0) {
        			cleaners[c.x][c.y] = false;
        			cleaners[cur[0]][cur[1]] = true;
        			c.x = cur[0];
        			c.y = cur[1];
        			break;
        		}
        		
        		for (int i = 0; i < 4; i++) {
        			int nx = cur[0] + dx[i];
        			int ny = cur[1] + dy[i];
        			
        			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
        			if (map[nx][ny] < 0 || cleaners[nx][ny] || visited[nx][ny]) continue;
        			
        			pq.offer(new int[] {nx, ny, cur[2] + 1});
        			visited[nx][ny] = true;
        		}
        	}
        }
		
	}
	
	static void clean() {
		for (Dust c : cleaner) {
			int total = 0;
			if (map[c.x][c.y] > 0) {
			    total = Math.min(20, map[c.x][c.y]);
			}
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
    			if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == -1) continue;
    			total += Math.min(20, map[nx][ny]);
			}
			
			int idx = -1;
			int min = 0;
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
    			if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] != -1) {
    				int sum = total - Math.min(20, map[nx][ny]);
        			
        			if (min < sum) {
        				min = sum;
        				idx = i;
        			}
    			}
    			else {
        			if (min < total) {
        				min = total;
        				idx = i;
        			}
    			}
    			
			}
			
			map[c.x][c.y] -= 20;
			if (map[c.x][c.y]< 0) map[c.x][c.y] = 0;
			for (int i = 0; i < 4; i++) {
				if (idx == i) continue;

				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
    			if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == -1) continue;
    			map[nx][ny] -= 20;
    			if (map[nx][ny] < 0) map[nx][ny] = 0;
			}
		}
	}
	
	static void dirt() {
	    boolean[][] isClean = new boolean[n][n];
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (map[i][j] > 0) {
	                map[i][j] += 5;
	            } else if (map[i][j] == 0) {
	                isClean[i][j] = true;
	            }
	        }
	    }
	    
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (isClean[i][j]) {
	                int sum = 0;
	                for (int s = 0; s < 4; s++) {
	                    int nx = i + dx[s];
	                    int ny = j + dy[s];
	                    
	                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
	                    
	                    if (map[nx][ny] > 0 && !isClean[nx][ny]) {
	                        sum += map[nx][ny];
	                    }
	                }
	                map[i][j] = sum / 10;
	            }
	        }
	    }
	}
}
