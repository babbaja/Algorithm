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
	static ArrayList<ArrayList<Node>> arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		for (int i = 0; i <= n; i++) arr.add(new ArrayList<>());
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr.get(n1).add(new Node(n2, v));
			arr.get(n2).add(new Node(n1, v));
		}
		
		int[] d = new int[n + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[1] = 0;
		bfs(1, d);
		
		int maxd = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) { 
			if (max < d[i]) {
				maxd = i;
				max = d[i];
			}
		}
		
		Arrays.fill(d, Integer.MAX_VALUE);
		d[maxd] = 0;
		d[0] = 0;
		bfs(maxd, d);
		max = Integer.MIN_VALUE;
		for (int i : d) { 
			max = Math.max(max, i);
		}
		System.out.println(max);
	}
	
	static void bfs(int s, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			return o2.v - o1.v;
		});
		pq.offer(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (d[cur.to] < cur.v) continue;
			
			for (Node next : arr.get(cur.to)) {
				int sum = next.v + cur.v;
				
				if (sum < d[next.to]) {
					d[next.to] = sum;
					pq.offer(new Node(next.to, sum));
				}
			}
		}
	}
}
