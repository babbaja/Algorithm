import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] time;
	static int[] price;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		time = new int[n + 1];
		price = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n + 2];
		
		for (int i = 1; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i]); //어제까지 벌었던 수익 
			if (i + time[i] <= n + 1) {
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + price[i]); // price를 안으로 
			}
		}
		
		System.out.println(Math.max(dp[n], dp[n + 1])); // 마지막날에 할 수 있는 경우에만 
	}	
}
