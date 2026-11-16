package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; ++i) {
			dp[i][i] = 1;
		}
		
		for (int i = 1; i < N; ++i) {
			if (arr[i] == arr[i + 1]) dp[i][i + 1] = 1;
		}
		
		for (int i = 2; i < N; ++i) {
			for (int j = 1; i + j <= N; ++j) {
				if (arr[j] == arr[i + j] && dp[j + 1][i + j - 1] == 1) dp[j][i + j] = 1;
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; ++i) {
		    st = new StringTokenizer(br.readLine());
		    int S = Integer.parseInt(st.nextToken());
		    int E = Integer.parseInt(st.nextToken());
		    sb.append(dp[S][E]).append("\n"); 
		}

		System.out.print(sb);
	} 

}
