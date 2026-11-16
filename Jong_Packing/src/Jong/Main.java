package Jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Back {
		String name;
		int v;
		int p;
		
		public Back(String name, int v, int p) {
			this.name = name;
			this.v = v;
			this.p = p;
		}
	}
	static Back[] back;
	static int[][] dp = new int[101][1001];
	static int N;
	static List<String> pick = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			N  = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			back = new Back[N];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				back[i] = new Back(str, v , p);
			}
			
			System.out.print(pack(W, 0) + " ");
			pickup(W, 0);
			System.out.println(pick.size());
			for (String str : pick) System.out.println(str);
		}
	}
	
	static int pack(int capa, int item) {
		if (item >= N) return 0;
		if (dp[capa][item] != 0) return dp[capa][item];
		
		//가져간다
		int tmp = 0;
		if (capa >= back[item].v) tmp  = pack(capa - back[item].v, item + 1) + back[item].p;
		//안가져간다
		int tmp2 = pack(capa, item + 1);
		
		int max = Math.max(tmp, tmp2);
		dp[capa][item] = max;
		
		return max;
	}
	
	static void pickup(int capa, int item) {
		if (N == item) return;
		
		if (pack(capa, item) == pack(capa,item + 1)) {
			pickup(capa, item + 1);
		}
		else {
			pick.add(back[item].name);
			pickup(capa - back[item].v, item + 1);
		}
	}
 
}
