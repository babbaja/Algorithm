import java.io.*;
import java.util.*;

public class Main {
	static Map<Integer, Integer> ladder;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ladder = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			ladder.put(n1, n2);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			ladder.put(n1, n2);
		}

		visited = new boolean[101];
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			if (curr[0] == 100) return curr[1];
			
			for (int i = 1; i <= 6; i++) {
				int next = curr[0] + i;
				
				if (ladder.containsKey(next)) {
					next = ladder.get(next);
				}
				if (next > 100) continue;
				if (visited[next]) continue;
				
				visited[next] = true;
				q.offer(new int[] {next, curr[1] + 1});
			}
		}
		return -1;
	}
}
