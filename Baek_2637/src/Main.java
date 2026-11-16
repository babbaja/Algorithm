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
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Node>> arr = new ArrayList<>();
		HashSet<Integer> nbasic = new HashSet<>();
		for (int i = 0; i <= n; i++) arr.add(new ArrayList<>());
		int[] D = new int[n + 1];
		int[] num = new int[n + 1];
		num[n] = 1;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			D[y]++;
			nbasic.add(x);
			arr.get(x).add(new Node(y, k));
		}
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(n, 1));
		
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			
			for (Node next : arr.get(tmp.to)) {
				num[next.to] += tmp.v * next.v;
				D[next.to]--;
				if (D[next.to] == 0) {
					q.offer(new Node(next.to, num[next.to]));
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if (!nbasic.contains(i)) {
				System.out.println(i + " " + num[i]);
			}
		}
	}
}
