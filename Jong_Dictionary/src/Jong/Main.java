package Jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] dp = new int[26][26];
    static boolean isCycle;
    static boolean[] visited, finished;
    static Stack<Character> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < 26; ++i) Arrays.fill(dp[i], -1);
	        visited = new boolean[26];
	        finished = new boolean[26];
	        stack = new Stack<>();
	        isCycle = false;
			
			String[] str = new String[N];
			for (int i = 0; i < N; ++i) {
				str[i] = br.readLine();
			}
			order(str);
			
			for (int i = 25; i >= 0; --i) {
	            if (!isCycle && !visited[i]) dfs(i);
	        }
			
			
	 
            if (isCycle) System.out.print("INVALID HYPOTHESIS");
            else {
                while (!stack.isEmpty()) System.out.print(stack.pop());
            }
            System.out.println();
		}
	}
	
	public static void dfs(int v) {
		if (isCycle) return; 
	    visited[v] = true;
	    for (int i = 0; i < 26; i++) {
	
	        if (dp[v][i] == 1) {
	            if (!visited[i]) dfs(i);
	            else if (!finished[i]) {
	            	isCycle = true;
	            	return;
	            }
	        }
	    }
	    finished[v] = true;
	    stack.push((char) (v + 'a'));
	}

	// 수정된 order 함수
	static void order(String[] str) {
	    for (int j = 1; j < str.length; ++j) {
	        int i = j - 1;
	        int len = Math.min(str[i].length(), str[j].length());
	        boolean diffFound = false; // 글자 차이를 찾았는지 확인하는 플래그

	        for (int k = 0; k < len; ++k) {
	            if (str[i].charAt(k) != str[j].charAt(k)) {
	                int a = str[i].charAt(k) - 'a';
	                int b = str[j].charAt(k) - 'a';
	                dp[a][b] = 1;
	                diffFound = true;
	                break;
	            }
	        }

	        // 루프가 끝났는데 글자 차이를 못 찾았고(접두어 관계)
	        // 앞 단어가 더 길다면 모순이다.
	        if (!diffFound && str[i].length() > str[j].length()) {
	            isCycle = true; // 모순 발생 플래그 설정
	            return; // 더 이상 그래프를 만들 필요가 없음
	        }
	    }
	}
}
