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
	static int N;
	static ArrayList<ArrayList<Integer>> arr;
	static int[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		check = new int[N];
		
		for (int i = 0; i <= N; ++i) {
			arr.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			arr.get(n1).add(n2);
			arr.get(n2).add(n1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			check[i] = Integer.parseInt(st.nextToken());
		}
		if (check[0] != 1) System.out.println(0); 
		else System.out.println(bfs(1) ? 1 : 0);
	}
	
	static boolean bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		HashSet<Integer> hash1 = new HashSet<>();
		HashSet<Integer> hash2 = new HashSet<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(start);
		visited[start] = true;
		int idx = 1;
		
		while (!queue.isEmpty()) {
			int tmp = queue.poll();
			//System.out.println(tmp);
			
			for (int next : arr.get(tmp)) {
				if (!visited[next]) {
					hash1.add(next);	
				}
			}
			
			int size = hash1.size();
			for (int i = 0; i < hash1.size(); ++i) {
				hash2.add(check[idx + i]);
			}
			
			if (!hash1.equals(hash2)) return false;
			

			for (int i = 0; i < size; ++i) {
				queue.offer(check[idx + i]);
				visited[check[idx + i]] = true;
			}
			idx += size;
			hash1.clear();
			hash2.clear();
			
		}
		
		
		return true;
	}

}
