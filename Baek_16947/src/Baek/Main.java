package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] subway;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] circle;
	static boolean[] visited;
	static int[] ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		subway = new int[N + 1];
		circle = new boolean[N + 1];
		visited = new boolean[N + 1];
		ans = new int[N + 1];
		
		arr = new ArrayList<>();
		for (int t = 0; t < N + 1; ++t) {
			arr.add(new ArrayList<Integer>());
			ans[t] = -1;
		}
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			arr.get(n1).add(n2);
			arr.get(n2).add(n1);
		}
		
		for (int i = 1; i <= N; ++i) {
			if (!circle[i]) dfs(i, i, 0);
		}
		
		for (int i = 1; i <= N; ++i) {
			if (circle[i]) {
				ans[i] = 0;
				distance(i);
			}
		}
		

		for (int i = 1; i <= N; ++i) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
	}

	
	static boolean dfs(int curr, int start, int depth) {
		circle[curr] = true;
		depth += 1;
		if (arr.get(curr).contains(start) && depth >= 3) {
			ans[curr] = 0;
			return true;
		}
		
		for (int next : arr.get(curr)) {
			if (!circle[next]) {
				if (dfs(next, start, depth)) {
					ans[next] = 0;
					return true;
				}
			}
		}
		circle[curr] = false;
		return false;
	}
	
	static void distance(int curr) {
		visited[curr] = true;
		for (int next : arr.get(curr)) {
			if (ans[next] == -1 && !visited[next]) {
				ans[next] = ans[curr] + 1;
				distance(next);
			}
		}
	}
	
}
