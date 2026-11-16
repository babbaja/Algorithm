import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int to;
		int v;
		
		public Node(int to, int v) {
			this.to = to;
			this.v = v;
		}
	}
	static int n;
	static int m;
	static int x;
	static ArrayList<ArrayList<Node>> arr;
	static int[][] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		dist = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			arr.add(new ArrayList<>());
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr.get(n1).add(new Node(n2, v));
		}
		for (int i = 1; i <= n; i++) bfs(i);
		
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			int distance = dist[i][x] + dist[x][i];
			max = Math.max(max, distance);
		}
		System.out.println(max);
	}
	
	static void bfs(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.v - o2.v;   
        });
		pq.offer(new Node(s, 0));
		dist[s][s] = 0;
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if (dist[s][curr.to] < curr.v) continue;
			
			for (Node next : arr.get(curr.to)) {
				int sum = curr.v + next.v;
				if (dist[s][next.to] > sum) {
					dist[s][next.to] = sum;
					pq.offer(new Node(next.to, sum));
				}
			}
		}
	}
}
