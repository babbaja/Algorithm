import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int id, p, r, t;
		
		public Node(int id, int p, int r, int t) {
			this.id = id;
			this.p = p;
			this.r = r;
			this.t = t;
		}
	}
	static PriorityQueue<Node> pq;
	static PriorityQueue<Node> wait;
	static Map<Integer, Node> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.p == o2.p) return o1.id - o2.id;
			return o2.p - o1.p;
		});
		wait = new PriorityQueue<>((o1, o2) -> {
			return o1.t - o2.t;
		});
		map = new HashMap<>();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			
			while (!wait.isEmpty()) {
				if (wait.peek().t > t) break;
				Node cur = wait.poll();
				if (map.get(cur.id).p != cur.p) continue;

				map.get(cur.id).t = 0;
				pq.offer(new Node(cur.id, cur.p, cur.r, 0));
			}
			
			if (command == 100) {
				init(st);
			}
			else if (command == 200) {
				int id = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				pq.offer(new Node(id, p, r, 0));
				map.put(id, new Node(id, p, r, 0));
			}
			else if (command == 300) {
				int id = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				Node cur = map.get(id);
				cur.p = p;
				if (cur.t == 0) pq.offer(new Node(id, p, cur.r, 0));
				else wait.offer(new Node(id, p, cur.r, cur.t));
			}
			else if (command == 400) {
				attack(t);
			}
		}
	}
	
	static void attack(int t) {
       StringBuilder sb = new StringBuilder();
       List<Integer> arr = new ArrayList<>();
       int cnt = 0, damage = 0;
       
       while (!pq.isEmpty()) {
    	   if (cnt >= 5) break;
    	   Node cur = pq.poll();
    	   if (map.get(cur.id).p != cur.p) continue;

    	   arr.add(cur.id);
    	   damage += cur.p;
    	   cnt++;
    	   wait.offer(new Node(cur.id, cur.p, cur.r, cur.r + t));
    	   map.get(cur.id).t = cur.t + t;
       }
       sb.append(damage).append(" ").append(cnt).append(" ");
       for (int n : arr) sb.append(n).append(" ");
       System.out.println(sb);
    }
	
	static void init(StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			int id = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(id, p, r, 0));
			map.put(id, new Node(id, p, r, 0));
		}
	}
}
