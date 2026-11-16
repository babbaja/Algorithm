import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] dist = new int[n + 1];
		List<List<Integer>> arr = new ArrayList<>();
		for (int i = 0; i <= n; i++) arr.add(new ArrayList<>());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			arr.get(n1).add(n2);
			dist[n2]++;
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (dist[i] == 0) {
				q.offer(i);
				
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for (int next : arr.get(cur)) {
				dist[next]--;
				if (dist[next] == 0) {
					q.offer(next);
				}
			}
		}
		System.out.println(sb);
	}
}
