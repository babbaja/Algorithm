package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] map;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		map = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; ++i) dp[i] = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 0;
		for (int i = 0; i < N; ++i) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			for (int j = 1; j <= map[i]; ++j) {
				if (i + j < N) {
//					System.out.println((dp[i] + 1) + " " + dp[i + j]);
					if (dp[i] == Integer.MAX_VALUE && dp[i + j] == Integer.MAX_VALUE) {
						dp[i + j] = 0;
					}
					else dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
				}
			}
		}
		 
//		for (int i = 0; i < N; ++i) {
//			System.out.print(dp[i] + " ");
//		}
		
		System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);

	}

}
