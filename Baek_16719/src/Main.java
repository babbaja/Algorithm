import java.io.*;
import java.util.*;

public class Main {
	static char[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		list = br.readLine().toCharArray();
		int len = list.length;
		visited = new boolean[len];

		dfs(0, len - 1);
	}
	
	static void dfs(int left, int right) {
		if (left > right) return;
		
		int minIdx = left;
		for (int i = left + 1; i <= right; i++) {
			if (list[minIdx] > list[i]) minIdx = i;
		}
		
		visited[minIdx] = true;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++) {
			if (visited[i]) sb.append(list[i]);
		}
		System.out.println(sb);
		
		dfs(minIdx + 1, right);
		dfs(left, minIdx - 1);
	}
}
