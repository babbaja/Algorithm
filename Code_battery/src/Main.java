import java.io.*;
import java.util.*;

public class Main {
	static class Module {
		int[] arr;
		int sum;
		
		public Module(int[] arr, int sum) {
			this.arr = arr;
			this.sum = sum;
		}
	}
	static List<Module> modules;
	static Set<String> set;
	static Module[] module = new Module[2];
	static int n;
	static int m;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		modules = new ArrayList<>();
		set = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int[] tmp = new int[5];
				tmp[0] = i * m + j;
				boolean[] visited = new boolean[n * m];
				visited[tmp[0]] = true;
				makeModule(tmp, map[i][j], 1, visited);
			}
		}
		
		for (int i = 0; i < modules.size(); i++) {
			module[0] = modules.get(i);
			select(i + 1, 1);
			module[0] = null;
		}
		
		System.out.println(max);
	}
	
	static void makeModule(int[] arr, int sum, int cnt, boolean[] visited) {
		if (cnt == 5) {
			int[] cur = arr.clone();
			Arrays.sort(cur);
			String key = Arrays.toString(cur);
	        if (set.add(key)) {
	            modules.add(new Module(cur, sum));
	        }
			return;
		}
		
		for (int i = 0; i < cnt; i++) {
			int x = arr[i] / m;
			int y = arr[i] % m;
			for (int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				int num = nx * m + ny; 
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[num]) continue;
				
				visited[num] = true;
				sum += map[nx][ny];
				arr[cnt] = num;
				makeModule(arr, sum, cnt + 1, visited);
				visited[num] = false;
				sum -= map[nx][ny];
				arr[cnt] = 0;
			}
		}
	}
	
	static void select(int s, int cnt) {
		if (cnt == 2) {
			checkTwo();
			return;
		}
	
		for (int i = s; i < modules.size(); i++) {
			module[1] = modules.get(i);
			select(s + 1, cnt + 1);
			module[1] = null;
		}
	}
	
	static void checkTwo() {
		int cell = 0;
		int i = 0;
		int j = 0;
		
		while (i < 5 && j < 5) {
			if (module[0].arr[i] == module[1].arr[j]) {
				cell += 1;
				i += 1;
				j += 1;
			}
			else if (module[0].arr[i] > module[1].arr[j]) j += 1;
			else i += 1;
		}
		
		if (cell == 2) max = Math.max(max, module[0].sum + module[1].sum); 
	}
}
