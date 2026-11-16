import java.io.*;
import java.util.*;

public class Main {
	static class Bear implements Comparable<Bear>{
		int v;
		int c;
		
		public Bear(int v, int c) {
			this.v = v;
			this.c = c;
		}
		
		@Override
		public int compareTo(Bear o1) {
			if (this.v == o1.v) return this.c - o1.c;
			return o1.v - this.v;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Bear[] bears = new Bear[k];
		long hi = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bears[i] = new Bear(v, c);
			hi = Math.max(hi, c);
		}
		Arrays.sort(bears);
		long low = 0;
		long answer = -1;
		while (low <= hi) {
			long mid = (low + hi) / 2;
			
			int like = 0;
			int cnt = 0;
			for (int i = 0; i < k && cnt < n; i++) {
				if (mid >= bears[i].c) {
					cnt++;
					like += bears[i].v;
				}
			}
			
			if (like >= m && cnt == n) {
				answer = mid;
				hi = mid - 1;
			}
			else low = mid + 1;
		}
		
		System.out.println(answer);
	}
}
