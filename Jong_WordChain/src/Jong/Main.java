package Jong;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] alpha;
	static int[] start;
	static int[] end;
	static List<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			String[] str = new String[N];
			alpha = new int[26][26];
			start = new int[26];
			end = new int[26];
			list = new LinkedList<>();
			
			for (int i = 0; i < N; ++i) {
				str[i] = br.readLine();
			}
			order(str);
			
			if (!check()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			getCircleOrTrain();
			for (int i : list) System.out.print(i + " ");
			if (list.size() != N + 1) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			int size = list.size() - 1;
			
			for (int i = size; i >= 0; --i) {
				System.out.print(str[i] + " ");
			}
			System.out.println();
		}
	}
	
	static void getCircleOrTrain() {
		
		for (int i = 0; i < 26; ++i) {
			if (end[i] == start[i] + 1) {
				getCircuit(i);
				return;
			}
		}
		
		for (int i = 0; i < 26; ++i) {
			if (end[i] > 0) {
				getCircuit(i);
				return;
			}
		}
		
		
	}
	
	static void getCircuit(int i) {
		for (int j = 0; j < 26; ++j) {
			while (alpha[i][j] > 0) {
				alpha[i][j]--;
				getCircuit(j);
			}
		}
		list.add(i);
	}
	
	static void order(String[] str) {
		for (int i = 0; i < N; ++i) {
			int len = str[i].length() - 1;
			int front = str[i].charAt(0) - 'a';
			int back = str[i].charAt(len) - 'a';
			
			alpha[front][back]++;
			start[back]++;
			end[front]++;
		}
	}
	
	static boolean check() {
		int plus1 = 0, minus1 = 0;
		for (int i = 0; i < 26; ++i) {
			int delta = end[i] - start[i];
			if (delta < -1 || delta > 1) return false;
			if (delta == 1) plus1++;
			if (delta == -1) minus1++;
		}
		
		return (plus1 == 1 && minus1 == 1) || (plus1 == 0 && minus1 == 0);
	}
}
