import java.io.*;
import java.util.*;

public class Main {
	static int[][] num = {
	    {1, 1, 1, 1, 1, 1, 0},
	    {0, 1, 1, 0, 0, 0, 0},
	    {1, 1, 0, 1, 1, 0, 1},
	    {1, 1, 1, 1, 0, 0, 1},
	    {0, 1, 1, 0, 0, 1, 1},
	    {1, 0, 1, 1, 0, 1, 1},
	    {1, 0, 1, 1, 1, 1, 1},
	    {1, 1, 1, 0, 0, 0, 0},
	    {1, 1, 1, 1, 1, 1, 1},
	    {1, 1, 1, 1, 0, 1, 1}
	};
	static int[][] led = new int[10][10];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int[] floor = new int[k];
		int curr = n;
		for (int i = k - 1; i >= 0; i--) {
			floor[i] = x % 10;
			x = x / 10;
		}
		
		ledCheck();

		int result = -1;
		for (int i = 1; i <= n; i++) {
			curr = i;
			int[] tmp = new int[k];
			for (int j = k - 1; j >= 0; j--) {
				tmp[j] = curr % 10;
				curr = curr / 10;
			}
			
			int cnt = 0;
			for (int j = 0; j < k; j++) cnt += led[tmp[j]][floor[j]];
//			System.out.println(cnt + " " + i);
			if (cnt <= p) result++;
		}
		System.out.println(result);
	}
	
	static void ledCheck() {
		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < 10; j++) {
				if (led[i][j] != 0) break;
				int cnt = 0;
				for (int k = 0; k < 7; k++) {
					if (num[i][k] != num[j][k]) cnt++;
				}
				led[i][j] = cnt;
				led[j][i] = cnt;
			}
		}
	}
	
}
