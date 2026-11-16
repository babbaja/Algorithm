import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int k;
	static int[] s;
	static int[][] dist;
	static ArrayList<ArrayList<Integer>> arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()); 
		s = new int[k];
		arr = new ArrayList<>();
		dist = new int[k][n + 1];
		for (int i = 0; i <= n; i++) arr.add(new ArrayList<>());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			arr.get(n1).add(n2);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			s[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < k; i++) {
			bfs(s[i], i);
		}
		
//		for (int i = 0; i < k; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(dist[i][j] + " ");
//            }
//            System.out.println();
//        }
		
		int min = Integer.MAX_VALUE;
		for (int j = 1; j <= n; j++) {
			int time = Integer.MIN_VALUE;
			for (int i = 0; i < k; i++) {
				time = Math.max(time, dist[i][j]);
			}
			if (time != Integer.MAX_VALUE) {
				min = Math.min(min, time);
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void bfs(int s, int idx) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		dist[idx][s] = 0;
		pq.offer(new int[] {s, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (dist[idx][cur[0]] < cur[1]) continue;
			
			for (int n : arr.get(cur[0])) {
				int sum = cur[1] + 1;
				if (sum < dist[idx][n]) {
					dist[idx][n] = sum;
					pq.offer(new int[] {n, sum});
				}
			}
		}
	}
}
