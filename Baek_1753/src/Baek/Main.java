package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int to;
		int v;
		
		public Node(int to, int v) {
			this.to = to;
			this.v = v;
		}
	}
	static ArrayList<ArrayList<Node>> node;
	static int[] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		node = new ArrayList<>();
		for (int i = 0; i <= v; ++i) node.add(new ArrayList<>());
		dist = new int[v + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < e; ++i) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			
			node.get(n1).add(new Node(n2, v1));
			//node.get(n2).add(new Node(n1, v1));
		}
		bfs(s);
		for (int i = 1; i < v + 1; ++i) {
			if (dist[i] != Integer.MAX_VALUE) System.out.println(dist[i]);
			else System.out.println("INF");
		}
	}
	
	static void bfs(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> {
			return a.v - b.v;
		});
		pq.offer(new Node(s, 0));
		dist[s] = 0;
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int curr = tmp.to;
			int v = tmp.v;
			
			if (v > dist[curr]) continue;
			
			for (Node node : node.get(curr)) {
				int next = node.to;
				int w = node.v;
				
				if (v + w < dist[next]) {
					dist[next] = v + w;
					pq.offer(new Node(next, v + w));
				}
			}
		}
	}

}
