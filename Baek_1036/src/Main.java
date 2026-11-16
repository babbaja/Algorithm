import java.io.*;
import java.util.*;

public class Main {
	static class Num implements Comparable<Num> {
		int[] val;
		
		public Num() {
			this.val = new int[60];
		}
		
		public void flatten() {
			for (int i = 0; i < 59; i++) {
				this.val[i + 1] += this.val[i] / 36;
				this.val[i] = this.val[i] % 36;
			}
		}
		
		@Override
		public int compareTo(Num o1) {
			this.flatten();
			o1.flatten();
			for (int i = 59; i >= 0; i--) {
				if (this.val[i] > o1.val[i]) return -1;
				if (this.val[i] < o1.val[i]) return 1;
			}
			return 0;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Num[] diff = new Num[36];
		for (int i = 0; i < 36; i++) diff[i] = new Num();
		Num result = new Num();
		
		for (int i = 0; i < n; i++) {
			char[] list = br.readLine().toCharArray();
			int len = list.length;
			for (int j = 0; j < len; j++) {
				int idx = len - j - 1;
				int ts = toInt(list[idx]);
				
				diff[ts].val[j] += 35 - ts;
				result.val[j] += ts;
			}
		}
		Arrays.sort(diff);
		int k = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < k && i < 36; i++) {
			for (int j = 0; j < 60; j++) {
				result.val[j] += diff[i].val[j];
			}
		}
		result.flatten();
		StringBuilder sb = new StringBuilder();
		boolean start = false;
		for (int i = 59; i >= 0; i--) {
			if (result.val[i] != 0) start = true;
			if (start) {
				sb.append(toChar(result.val[i]));
			}
		}
        if (!start) System.out.println("0");
        else System.out.println(sb.toString());
	}
	
	static int toInt(char c) {
		if (c >= '0' && c <= '9') return c - '0';
		return c - 'A' + 10;
	}
	
	static char toChar(int num) {
		if (num >= 0 && num <= 9) return (char)(num + '0');
		return (char)(num - 10 + 'A');
	}
}
