import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		
		while (true) {
			if (sum >= s) {
				min = Math.min(min, right - left);
				sum -= arr[left++];
			}
			else if (right == n) break;
			else {
				sum += arr[right++];
			}
		}
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}
}
