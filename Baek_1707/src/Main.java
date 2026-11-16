import java.io.*;
import java.util.*;

public class Main {
	static int[] node;
	static ArrayList<ArrayList<Integer>> arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			node = new int[v + 1];
			arr = new ArrayList<>();
			for (int i = 0; i <= v; i++) arr.add(new ArrayList<>());
			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				arr.get(n1).add(n2);
				arr.get(n2).add(n1);
			}
			
			boolean binary = true;
			for (int i = 1; i <= v; i++) {
				if (node[i] == 0) {
					binary = dfs(i, 1);
				}
				if (!binary) break; 
			}
			
			System.out.println(binary ? "YES" : "NO");
		}
		
	}
	
	static boolean dfs(int cur, int num) {
		if (node[cur] != 0) {
			return node[cur] == num;
		}
		node[cur] = num;
		int next = 0;
		if (num == 1) next = 2;
		else if (num == 2) next = 1;
		
		for (int n : arr.get(cur)) {
			if (node[n] == 0) {
				if (!dfs(n, next)) return false;
			}
			else {
				if (node[n] != next) return false;
			}
		}
		return true;
	}
}
