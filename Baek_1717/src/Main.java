import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) parent[i] = i;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			int p1 = find(n1);
			int p2 = find(n2);
			if (num == 0) {
				parent[p2] = p1;
			}
			else {
				System.out.println(p1 == p2 ? "YES" : "NO");
			}
		}
	}
	
	static int find(int s) {
		if (parent[s] == s) return s;
		return parent[s] = find(parent[s]);
	}
}
