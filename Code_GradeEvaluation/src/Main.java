import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] score = new int[4][n];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
				score[3][j] += score[i][j];
			}
		}
		
		int[][] grade = new int[4][n];
		
		for (int s = 0; s < 4; s++) {
		    int[] count = new int[3001];
		    int[] rank = new int[3001];

		    for (int i = 0; i < n; i++) {
		        count[score[s][i]]++;
		    }

		    int higher = 0;

		    for (int i = 3000; i >= 0; i--) {
		        if (count[i] == 0) continue;

		        rank[i] = higher + 1;
		        higher += count[i];
		    }

		    for (int i = 0; i < n; i++) {
		        grade[s][i] = rank[score[s][i]];
		    }
		}
		StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grade[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
	}
}
