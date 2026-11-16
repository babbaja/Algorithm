import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		
		long max = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long low = 1;
		long hi = max;
		
		while (low <= hi) {
			long mid = (low + hi) / 2;
//			System.out.println(low + " " + mid + " " + hi);
			
			int cnt = 0;
			for (int i = 0; i < n; ++i) {
				cnt += arr[i] / mid;
			}
			
			if (cnt >= k) {
				low = mid + 1;
			}
			else if (cnt < k) {
				hi = mid - 1;
			}
		}
		
		System.out.println(low - 1);
	}
}
