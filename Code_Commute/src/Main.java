import java.io.*;
import java.util.*;

public class Main {
	static int n, m, s, t;
	static ArrayList<ArrayList<Integer>> arr, rra;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		rra = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			arr.add(new ArrayList<>());
			rra.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			arr.get(n1).add(n2);
			rra.get(n2).add(n1);
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		boolean[] fromS = new boolean[n + 1];
		boolean[] toT = new boolean[n + 1];
		boolean[] fromT = new boolean[n + 1];
		boolean[] toS = new boolean[n + 1];
		
		bfs(s, t, fromS, arr);
		bfs(t, -1, toT, rra);
		bfs(t, s, fromT, arr);
		bfs(s, -1, toS, rra);
		
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (i == s || i == t) continue;
			if (fromS[i] && toT[i] && fromT[i] && toS[i]) cnt += 1;
		}
		System.out.println(cnt);
	}
	
	static void bfs(int num, int target, boolean[] visited, ArrayList<ArrayList<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(num);
		visited[num] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (cur == target) continue;
			
			for (int next : graph.get(cur)) {
				if (visited[next]) continue;
				visited[next] = true;
				q.offer(next);
			}
		}
	}
		
}
