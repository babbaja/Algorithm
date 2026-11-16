import java.io.*;
import java.util.*;

public class Main {
	static class FireBall {
		int r;
		int c;
		int m;
		int s;
		int d;
		
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static int n;
	static int m;
	static int k;
	static ArrayList<FireBall>[][] arr;
	static Queue<FireBall> q;
	static int[] dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		arr = new ArrayList[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				arr[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			FireBall fire = new FireBall(r, c, m, s, d);
			q.offer(fire);
			arr[r][c].add(fire);
		}
		
		System.out.println(start());
		
	}
	
	static int start() {
		int T = 0;
		while (T++ < k) {
			while (!q.isEmpty()) {
				FireBall curr = q.poll();
				
				int nx = (curr.r + dx[curr.d] * (curr.s % n) - 1 + n) % n + 1;
				int ny = (curr.c + dy[curr.d] * (curr.s % n) - 1 + n) % n + 1;
				
				arr[curr.r][curr.c].remove(curr);
				curr.r = nx;
				curr.c = ny;
				arr[nx][ny].add(curr);
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (arr[i][j].size() >= 2) {
						int size = arr[i][j].size();
						int sumM = 0;
						int sumS = 0;
						boolean isodd = false;
						boolean iseven = false;
						for (FireBall fire : arr[i][j]) {
							sumM += fire.m;
							sumS += fire.s;
							if (fire.d % 2 == 0) isodd = true;
							else iseven = true;
						}
						arr[i][j].clear();
						sumM = sumM / 5;
						sumS = sumS / size;
						if (sumM == 0) continue;
						if (isodd && iseven) {
							for (int k = 1; k <= 7; k += 2) {
								FireBall tmp = new FireBall(i, j, sumM, sumS, k);
								arr[i][j].add(tmp);
								q.offer(tmp);
							}
						}
						else {
							for (int k = 0; k <= 6; k += 2) {
								FireBall tmp = new FireBall(i, j, sumM, sumS, k);
								arr[i][j].add(tmp);
								q.offer(tmp);
							}
						}
					}
					else if (arr[i][j].size() == 1) q.offer(arr[i][j].get(0));
				}
			}
		}
		int sum = 0;
		while (!q.isEmpty()) {
			FireBall fb = q.poll();
			sum += fb.m;
		}
		return sum;
		
	}
}
