package Jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] memo = new int[101][101];
	static long[] arr;
	static long[] arr2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			for (int i = 0; i < 101; ++i) {
				for (int j = 0; j < 101; ++j) {
					memo[i][j] = -1;
				}
			}
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			arr = new long[n];
			arr2 = new long[m];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; ++i) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; ++i) {
				arr2[i] = Long.parseLong(st.nextToken());
			}
			System.out.println(JLis(-1, -1) - 2);
			
		}
	}
	
	// 0, 0부터 확인하기 위해서 -1 -1 부터 시작 
	static int JLis(int idx, int idx2) {
		if (memo[idx + 1][idx2 + 1] != -1) return memo[idx + 1][idx2 + 1];
		
		memo[idx + 1][idx2 + 1] = 2;
		long a = idx == -1 ? Integer.MIN_VALUE : arr[idx];
		long b = idx2 == -1 ? Integer.MIN_VALUE : arr2[idx2];
		long max = Math.max(a, b);
		
		for (int nx = idx + 1; nx < arr.length; ++nx) {
			if (max < arr[nx]) {
				memo[idx + 1][idx2 + 1] = Math.max(memo[idx + 1][idx2 + 1], JLis(nx, idx2) + 1);
			}
		}
		for (int ny = idx2 + 1; ny < arr2.length; ++ny) {
			if (max < arr2[ny]) {
				memo[idx + 1][idx2 + 1] = Math.max(memo[idx + 1][idx2 + 1], JLis(idx, ny) + 1);
			}
		}
		
		return memo[idx + 1][idx2 + 1];
	}

}
