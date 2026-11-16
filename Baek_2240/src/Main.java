import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int[] jadoo = new int[t + 1];
		int[][] dp = new int[t + 1][w + 1];
		
		for (int i = 1 ; i <= t; i++) {
			st = new StringTokenizer(br.readLine());
			jadoo[i] = Integer.parseInt(st.nextToken());
			
			dp[i][0] = dp[i - 1][0] + (jadoo[i] == 1 ? 1 : 0);
			for (int j = 1; j <= w; j++) {
				int pos = (j % 2 == 0 ? 1 : 2);
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + (jadoo[i] == pos ? 1 : 0);
			}
		}
		
		int max = 0;
		for (int i = 1; i <= w; i++) {
			max = Math.max(dp[t][i], max);
		}
		System.out.println(max);
	}
}
