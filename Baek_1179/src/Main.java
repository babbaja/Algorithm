import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int v;
		
		public Node(int from, int to, int v) {
			this.from = from;
			this.to = to;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}
	}
	static ArrayList<ArrayList<Node>> arr;
	static boolean[] visited;
	static int[] parent;
	static Queue<Node> kq = new PriorityQueue<>();	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		for (int i = 0; i <= n; ++i) arr.add(new ArrayList<>());
		visited = new boolean[n + 1];
		parent = new int[n + 1];
		for (int i = 1; i <= n; ++i) parent[i] = i;
		
		
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr.get(n1).add(new Node(n1, n2, v));
			arr.get(n2).add(new Node(n2, n1, v));
			kq.offer(new Node(n1, n2, v));
		}
		for (int i = 0; i <= n; ++i) Collections.sort(arr.get(i)); 
		
		
		//System.out.println(bfs(1));
		System.out.println(kruskal());
	}
	
	static int find(int s) {
		if (s == parent[s]) return s;
		return parent[s] = find(parent[s]);
	}
	
	static int kruskal() {
		int size = kq.size();
		int total = 0;
		for(int i = 0; i < size; ++i) {
			Node node = kq.poll();
			int toP = find(node.to);
			int fromP = find(node.from);
			
			if(toP != fromP) {
				total += node.v;
				parent[fromP] = toP;
			}
		}
		return total;
	}
	
	static int bfs(int s) {
		int sum = 0;
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, s, 0));
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			
			if (visited[tmp.to]) continue;

			visited[tmp.to] = true;
			sum += tmp.v;
			for (Node next : arr.get(tmp.to)) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		return sum;
	}

}
