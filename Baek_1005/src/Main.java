import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<ArrayList<Integer>> arr;
	static int[] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			arr = new ArrayList<>();
			for (int i = 0; i <= n; ++i) arr.add(new ArrayList<>());

			int[] time = new int[n + 1];
			int[] dist = new int[n + 1];
			int[] dp = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; ++i) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < k; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				arr.get(n1).add(n2);
				dist[n2]++;
			}
			
			int target = Integer.parseInt(br.readLine());
			
			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= n; ++i) {
				if (dist[i] == 0) {
					q.offer(i);
					dp[i] = time[i];
				}
			}
			
			while (!q.isEmpty()) {
				int tmp = q.poll();		
				if (tmp == target && dist[tmp] == 0) break;
		
				for (int next : arr.get(tmp)) {
					dp[next] = Math.max(dp[tmp], dp[next]);
					dist[next]--;
					
					if (dist[next] == 0) {
						q.offer(next);
						dp[next] += time[next];
					}
				}
			}
			
			System.out.println(dp[target]);
			
//			for (int i = 1; i <= n; ++i) System.out.print(dp[i] + " ");
		}
	}
}
