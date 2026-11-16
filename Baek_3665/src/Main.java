import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			int[] D = new int[n + 1];
			boolean[][] visited = new boolean[n + 1][n + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					visited[arr[i]][arr[j]] = true;
					D[arr[j]]++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				if (visited[n1][n2]) {
					visited[n1][n2] = false;
					D[n2]--;
					visited[n2][n1] = true;
					D[n1]++;
				}
				else if (visited[n2][n1]) {
					visited[n2][n1] = false;
					D[n1]--;
					visited[n1][n2] = true;
					D[n2]++;
				}
			}
			
			List<Integer> result = new ArrayList<>();
			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				if (D[arr[i]] == 0) q.offer(arr[i]);
			}

			boolean check = false;
			while (!q.isEmpty()) {
				if (q.size() > 1) {
					check = true;
					break;
				}
				int cur = q.poll();
				result.add(cur);
				
				for (int i = 1; i <= n; i++) {
					if (visited[cur][i]) {
						D[i]--;
						
						if (D[i] <= 0) q.offer(i);
					}
				}
			}
			
			if (check) System.out.println("?");
			else if (result.size() != n) System.out.println("IMPOSSIBLE");
			else {
				for (int i : result) System.out.print(i + " ");
				System.out.println();
			}
		}
	}
}