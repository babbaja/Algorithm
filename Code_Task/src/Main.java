import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int cnt = 1 << h;
		int node= 1 << (h + 1);
		
		int[][] leaf = new int[cnt][k];
		int[] next = new int[cnt];
		Queue<Integer>[] nodes = new LinkedList[node + 1];
		for (int i = 0; i <= node; i++) nodes[i] = new LinkedList<>();
		
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				leaf[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for (int day = 1; day <= r; day++) {
			for (int i = 1; i < cnt; i++) {
				int child = 0;
				if (day % 2 == 1) child = i * 2;
				else child = i * 2 + 1;
				
				if (nodes[child].isEmpty()) continue;
				int task = nodes[child].poll();
				
				if (i == 1) answer += task;
				else nodes[i].offer(task);
			}
			
			for (int i = 0; i < cnt; i++) {
				if (next[i] >= k) continue;
				
				int task = leaf[i][next[i]];
				next[i]++;
				
				nodes[cnt + i].offer(task);
			}
		}
		
		System.out.println(answer);
		
	}
}
