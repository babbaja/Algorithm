package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N + 1][M + 1];
		
		dp[0][S] = 1;
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j <= M; ++j) {
				if (dp[i - 1][j] == 1) {
					if (j - arr[i] >= 0) {
						dp[i][j - arr[i]] = 1;
					}
					if (j + arr[i] <= M) {
						dp[i][j + arr[i]] = 1;
					}
				}
			}
		}
		
		boolean answer = false;
		for (int i = M; i >= 0; --i) {
			if (dp[N][i] == 1) {
				System.out.println(i);
				answer = true;
				break;
			}
		}
		if (!answer) System.out.println(-1);
	}

}
