import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int low = 0;
		int hi = arr[n - 1];
		
		while (low <= hi) {
			int mid = (low + hi) / 2;
			
			int cnt = 1;
			int prev = arr[0];
			for (int i = 1; i < n; i++) {
				if (arr[i] - prev >= mid) {
					cnt++;
					prev = arr[i];
				}
			}
			
			if (cnt >= c) {
				low = mid + 1;
			}
			else {
				hi = mid - 1;
			}
		}
		
		System.out.println(hi);
	}
}
