import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static ArrayList<int[]> home;
	static ArrayList<int[]> chicken;
	static ArrayList<int[]> selected;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		selected = new ArrayList<>();
		
		for (int i = 0; i < n; i++ ) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chicken.add(new int[] {i, j});
				else if (map[i][j] == 1) home.add(new int[] {i, j});
			}
		}
		
		select(0, 0);
		System.out.println(min);
	}
	
	static void select(int s, int cnt) {
		if (cnt == m) {
			distance();
			return;
		}
		for (int i = s; i < chicken.size(); i++) {
			int[] cur = chicken.get(i);
			selected.add(new int[] {cur[0], cur[1]});
			select(i + 1, cnt + 1);
			selected.remove(selected.size() - 1);
		}
	}
	
	static void distance() {
		int sum = 0;
		
		for (int[] h : home) {
			int d = Integer.MAX_VALUE;
			for (int[] ch : selected) {
				int cost = Math.abs(h[0] - ch[0]) + Math.abs(h[1] - ch[1]);
				d = Math.min(d, cost);
			}
			sum += d;
		}
		min = Math.min(min, sum);
	}
}
