package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] visited;
	static Queue<Integer> input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		
		for (int i = 0; i < N + 1; ++i) {
			arr.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			arr.get(n1).add(n2);
			arr.get(n2).add(n1);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new LinkedList<>();
		for (int i = 0; i < N; ++i) {
			input.offer(Integer.parseInt(st.nextToken()));
		}
		int first = input.poll();
		if (first != 1) System.out.println(0);
		else System.out.println(dfs(1) ? 1 : 0);
		
	}
	
	static boolean dfs(int idx) {
		HashSet<Integer> hash = new HashSet<>();
		visited[idx] = true;
		
		for (int next : arr.get(idx) ) {
			if (!visited[next]) {
				hash.add(next);
			}
		}
		
		while (!input.isEmpty()) {
			int num = input.peek();
			if (hash.contains(num) ) {
				hash.remove(num);
				input.poll();
				dfs(num);
			}
			else return false;
		}
		return true;
	}

}
