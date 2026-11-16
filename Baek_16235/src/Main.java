import java.io.*;
import java.util.*;

public class Main {
	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;
		
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static int[][] plus;
	static Queue<Tree> trees;
	static int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][n + 1];
		plus = new int[n + 1][n + 1];
		trees = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = 5;
				plus[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			trees.add(new Tree(x, y, age));
		}
		
		Collections.sort((LinkedList<Tree>) trees);
		
		
		while (k-- > 0) {
			Queue<Tree> live = new LinkedList<>(); 
            Queue<Tree> die = new LinkedList<>();
            
            int size = trees.size();
            for(int i = 0; i < size; i++) {
                Tree tree = trees.poll(); 

                if (map[tree.x][tree.y] >= tree.age) {
                    map[tree.x][tree.y] -= tree.age;
                    tree.age += 1;
                    live.add(tree); 
                } 
                else die.add(tree); 
            }
			
			while (!die.isEmpty()) {
				Tree t = die.poll();
				map[t.x][t.y] += t.age / 2; 
			}
			
			for (Tree tree : live) {
				if (tree.age % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nx = tree.x + dx[i];
						int ny = tree.y + dy[i];
						if (nx < 1 || ny < 1 || nx >= n + 1 || ny >= n + 1) continue;
						
						trees.add(new Tree(nx, ny, 1));
					}
				}
			}
			trees.addAll(live);
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					map[i][j] += plus[i][j];
				}
			}
		}
		System.out.println(trees.size());
	}
}


