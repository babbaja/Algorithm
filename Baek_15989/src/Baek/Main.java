package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dp = new int[10001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		dp[0] = 1;
		
		for (int i = 1; i < 10001; ++i) {
			dp[i] += dp[i - 1];
		}
		for (int i = 2; i < 10001; ++i) {
			dp[i] += dp[i - 2];
		}
		for (int i = 3; i < 10001; ++i) {
			dp[i] += dp[i - 3];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			sb.append(dp[num]).append("\n");
		}
		
		System.out.println(sb);
	}

}
