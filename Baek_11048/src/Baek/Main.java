package Baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		candy();
		System.out.println(dp[N - 1][M - 1]);
	}
	
	static void candy() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (i == 0 && j == 0) {
					dp[i][j] = map[i][j];
					continue;
				}
				dp[i][j] = map[i][j]
						+ maxValue(i, j);
			}
		}
	}
	
	static int maxValue(int i, int j) {
		if (i == 0) {
			return dp[i][j - 1];
		}
		if (j == 0) {
			return dp[i - 1][j];
		}
		int value = Math.max(Math.max(dp[i - 1][j - 1], dp[i][j - 1]), Math.max(dp[i - 1][j - 1], dp[i - 1][j]));
		return value;
	}
}
