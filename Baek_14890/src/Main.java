import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (isRoad(map[i], l)) cnt += 1;
		}
		for (int i = 0; i < n; i++) {
			int[] tmp = new int[n];
			for (int j = 0; j < n; j++) {
				tmp[j] = map[j][i];
			}
			if (isRoad(tmp, l)) cnt += 1;
		}
		System.out.println(cnt);
	}
	
	static boolean isRoad(int[] arr, int l) {
		int n = arr.length;
		int prev = arr[0];
		int cnt = 1;
		for (int i = 1; i < n; i++) {
			if (prev == arr[i]) {
				cnt++;
			}
			else if (prev - 1 == arr[i]) {
				cnt = 0;
				for (int j = 0; j < l && i + j < n; j++) {
					if (arr[i + j] == prev - 1) cnt++;
				}
				if (cnt < l) return false;
				i += l - 1;
				if (i >= n) break;
				prev = arr[i];
				cnt = 0;
			}
			else if (prev + 1 == arr[i]) {
				if (cnt < l) return false;
				prev = arr[i];
				cnt = 1;
			}
			else return false;
		}
		return true;
	}
}
