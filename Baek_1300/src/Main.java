import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		int low = 1;
		int hi = m;
		
		while (low <= hi) {
			int mid = (low + hi) / 2;
			
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				cnt += Math.min(n, mid / i);
			}
			
			if (cnt >= m) {
				hi = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		System.out.println(low);
	}
}
