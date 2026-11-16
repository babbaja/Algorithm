package Baek;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[K + 1];
			int[][] sum = new int[K + 1][K + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i][i] = arr[i];
			}
			
			
			for (int len = 1; len < K; ++len) {
				for (int i = 1; i + len <= K; ++i) {
					sum[i][i + len] = sum[i][i + len - 1] + arr[i + len];
				}
			}
			
			int[][] dp = new int[K + 1][K + 1];			
			for (int len = 1; len <= K; ++len) {
				for (int i = 1; i + len <= K; ++i) {
					int j = i + len;
					dp[i][j] = Integer.MAX_VALUE;
					
					for (int k = i; k < j; ++k) {
						dp[i][j] = Math.min(dp[i][j], sum[i][j] + dp[i][k] + dp[k + 1][j]);
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
		
	}
}
