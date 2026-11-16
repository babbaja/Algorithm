import java.io.*;
import java.util.*;

public class Main {
	static char[][] wheel;
	static char[] two;
	static char[] three;
	static char[] four;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());

		wheel = new char[5][8];
		for (int i = 1; i < 5; i++) {
			wheel[i] = br.readLine().toCharArray();
		}
		
		int n = Integer.parseInt(br.readLine());
		int[][] circle = new int[n][2];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int direc = Integer.parseInt(st.nextToken());
			
			circle[i][0] = num;
			circle[i][1] = direc;
		}
		
		int T = 0;
		while (T < n) {
			int turn = circle[T][0];
			int direc = circle[T][1];
			int[][] move = new int[5][2];
			move[turn][0] = 1;
			move[turn][1] = direc;
			
			int left = turn - 1;
			int right = turn + 1;
			
			int j = 0;
			while (j < 3) {
				if (left >= 1) {
					if (wheel[left + 1][6] != wheel[left][2]) {
						move[left][0] = 1;
						move[left][1] = move[left + 1][1] * -1;
					}
					left -= 1;
				}
				if (right <= 4) {
					if (wheel[right - 1][2] != wheel[right][6]) {
						move[right][0] = 1;
						move[right][1] = move[right - 1][1] * -1;
					}
					right += 1;
				}
				j++;
			}
			for (int i = 1; i < 5; i++) {
				if (move[i][0] == 1) rotate(i, move[i][1]);
			}
			T++;
		}
		
		int sum = 0;
		int score = 1;
		for (int i = 1; i < 5; i++) {
			if (wheel[i][0] == '1') sum += score;
			score = score * 2;
		}
		System.out.println(sum);
		
	}
	
	static void rotate(int num, int direc) {
		char[] change = wheel[num];
		
		if (direc == -1) {
			char ch = change[0];
			for (int i = 0; i < 7; i++) {
				change[i] = change[i + 1];
			}
			change[7] = ch;
		}
		else if (direc == 1) {
			char ch = change[7];
			for (int i = 7; i > 0; i--) {
				change[i] = change[i - 1];
			}
			change[0] = ch;
		}
	}
}
