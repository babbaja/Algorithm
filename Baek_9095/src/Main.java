import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int T = Integer.parseInt(st.nextToken());
			
			for (int t = 0; t < T; ++t) {
				int num = Integer.parseInt(br.readLine());
				
				int[] dp = new int[num + 1];
				dp[1] = 1;
				if (num >= 2) {
					dp[2] = 2;
				}
				if (num >= 3) {
					dp[3] = 4;
				}
				
				for (int i = 4; i <= num; ++i) {
					dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
				}
				
				System.out.println(dp[num]);
			}
	}
}
