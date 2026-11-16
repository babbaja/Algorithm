import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] dp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(dp[i], max);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(max).append("\n");
		Stack<Integer> stack = new Stack<>();
		for (int i = n - 1; i >= 0; i--) {
			if (dp[i] == max) {
				stack.add(arr[i]);
				max--;
			}
		}
		
		while (!stack.isEmpty()) sb.append(stack.pop() + " ");
		System.out.println(sb);
	}
}
