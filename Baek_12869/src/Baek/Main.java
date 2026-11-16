package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] scv;
	static int[][] attack = {
		    {9, 3, 1}, {9, 1, 3},
		    {3, 9, 1}, {3, 1, 9},
		    {1, 9, 3}, {1, 3, 9}
		};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		scv = new int[3]; 
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {scv[0], scv[1], scv[2], 0});
		boolean[][][] visited = new boolean[61][61][61];
		visited[scv[0]][scv[1]][scv[2]] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			if (tmp[0] == 0 && tmp[1] == 0 && tmp[2] == 0) return tmp[3];
			
			for (int i = 0; i < 6; ++i) {
				int s = Math.max(tmp[0] - attack[i][0], 0);
				int c = Math.max(tmp[1] - attack[i][1], 0);
				int v = Math.max(tmp[2] - attack[i][2], 0);
				
				if (visited[s][c][v]) continue;
				visited[s][c][v] = true;
				queue.offer(new int[] {s, c, v, tmp[3] + 1});
			}
		}
		
		return -1;
	}
}
