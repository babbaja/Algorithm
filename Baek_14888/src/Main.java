import java.io.*;
import java.util.*;

public class Main {
	static class Operator {
		int idx;
		char oper;
		
		public Operator (int idx, char oper) {
			this.idx = idx;
			this.oper = oper;
		}
	}
	static int n;
	static int[] num;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static ArrayList<Operator> oper;
	static ArrayList<Operator> selected;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		oper = new ArrayList<>();
		selected = new ArrayList<>();
		char[] list = new char[] {'+', '-', '*', '/'};
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		for (int i = 0; i < 4; ++i) {
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; ++j) {
				oper.add(new Operator(idx++, list[i]));
			}
		}
		
		select(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void select(int cnt) {
		if (cnt == n - 1) {
			calc();
			return;
		}
		for (int i = 0; i < oper.size(); ++i) {
			if (!selected.contains(oper.get(i))) {
				selected.add(oper.get(i));
				select(cnt + 1);
				selected.remove(selected.size() - 1);
			}
		}
			
	}
	
	static void calc() {
		int tmp = num[0];
		
		for (int i = 0; i < selected.size(); ++i) {
			char ch = selected.get(i).oper;
			
			if (ch == '+') {
				tmp = tmp + num[i + 1];
			}
			else if (ch == '-') {
				tmp = tmp - num[i + 1];
			}
			else if (ch == '*') {
				tmp = tmp * num[i + 1];
			}
			else if (ch == '/') {
				tmp = tmp / num[i + 1];
			}
		}
		
		max = Math.max(tmp, max);
		min = Math.min(tmp, min);
	}
}
