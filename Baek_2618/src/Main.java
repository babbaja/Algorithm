import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] dp;
	static ArrayList<int[]> events;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dp = new int[m + 1][m + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			events.add(new int[] {r, c});
			Arrays.fill(dp[i], -1);
		}
		Arrays.fill(dp[m], -1);
		
		solve(0, 0);
	}
	
	static int solve(int p1, int p2) {
		int next = Math.max(p1, p2) + 1;
		if (next == m + 1) return 0;
		
		if (dp[p1][p2] != -1) return dp[p1][p2];

		int left = distance(1, p1, next) + solve(next, p2);
		int right = distance(2, p2, next) + solve(p1, next);
		return dp[p1][p2] = Math.min(left, right);
	}
	
	static int distance(int s, int e, int type) {
		int[] p1, p2;
		
		if (s == 0) {
			if (type == 1) p1 = new int[] {1, 1};
			else p1 = new int[] {n, n};
		}
		else p1 = events.get(s);
		p2 = events.get(e);
		
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
	
	static void trace(int p1, int p2) {
        int nextEvent = Math.max(p1, p2) + 1;
        if (nextEvent == m + 1) return;
        
        int cost1 = distance(1, p1, nextEvent) + solve(nextEvent, p2);
        int cost2 = distance(2, p2, nextEvent) + solve(p1, nextEvent);
        
        if (dp[p1][p2] == cost1) {
            System.out.println(1);
            trace(nextEvent, p2);
        } 
        else {
            System.out.println(2);
            trace(p1, nextEvent);
        }
    }
}
