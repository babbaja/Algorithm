import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] dp = new int[n + 1];
		int[] parent = new int[n + 1];
		Arrays.fill(dp, 100001);
		dp[1] = 0;
		for (int i = 2; i <= n; i++) {
			dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
			parent[i] = i - 1;
			if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
				dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
				parent[i] = i / 2;
			}
			if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
				dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
				parent[i] = i / 3;
			}
		}
		
		System.out.println(dp[n]);
		
		StringBuilder sb = new StringBuilder();
        while (n > 0) { 
            sb.append(n).append(" ");
            n = parent[n]; 
        }
        System.out.println(sb);
	}
}
