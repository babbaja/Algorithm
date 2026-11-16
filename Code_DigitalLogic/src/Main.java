import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[str.length() - k + 1];
		long mask = (1L << k) - 1;
		
		
		long value = 0;
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			int bit = str.charAt(i) - '0';
			
			value = ((value << 1) | bit) & mask;
			
			if (i >= k - 1) {
				arr[index++] = value;
			}
		}
		Arrays.sort(arr);
		int cnt = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) {
				cnt++;
			}
			else cnt = 1;
			
			if (cnt >= m) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
}