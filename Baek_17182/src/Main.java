import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int k;
	static int[][] dist;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dist = new int[n][n];
		visited = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		visited[k] = true;
		dfs(k, 0, 1);
		System.out.println(min);
	}
	
	static void dfs(int s, int sum, int cnt) {
		if (cnt == n) {
			min = Math.min(sum, min);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			dfs(i, sum + dist[s][i], cnt + 1);
			visited[i] = false;
			
		}
	}
}
