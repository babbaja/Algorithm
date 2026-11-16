import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int low = 1;
		int hi = arr[n - 1];
		
		while (low <= hi) {
			int mid = (low + hi) / 2;
			
			int cnt = 0;
			int cur = 0;
			for (int i = 0; i < n; i++) {
				if (cur < arr[i]) {
					cnt++;
					cur = arr[i] + mid - 1;
				}
			}
			
			if (k >= cnt) {
				hi = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		System.out.println(low);
	}
}
