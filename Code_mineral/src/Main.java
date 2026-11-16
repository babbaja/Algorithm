import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int T;
    static int[][] map;
    static int[][][] dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new int[T + 1][N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i <= T; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        
        int[][] from = new int[N][N];
        int[][] to = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    from[i][j] = map[i][j];
                    continue;
                }
                int prev = Integer.MIN_VALUE;
                if (i > 0) {
                    prev = Math.max(prev, from[i - 1][j]);
                }
                if (j > 0) {
                    prev = Math.max(prev, from[i][j - 1]);
                }
                from[i][j] = prev + map[i][j];
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (i == N - 1 && j == N - 1) {
                    to[i][j] = map[i][j];
                    continue;
                }
                int next = Integer.MIN_VALUE;
                if (i + 1 < N) {
                    next = Math.max(next,to[i + 1][j]);
                }
                if (j + 1 < N) {
                    next = Math.max(next, to[i][j + 1]);
                }
                to[i][j] = next + map[i][j];
            }
        }
        int max = from[N - 1][N - 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cost = dfs(i, j, T);
                if (cost != Integer.MIN_VALUE) {
                    max = Math.max(max, cost + from[i][j] + to[i][j]);
                }
            }
        }
        System.out.println(max);
    }
    
    static int dfs(int x, int y, int t) {
        if (t == 0) return 0;
        if (dp[t][x][y] != Integer.MIN_VALUE) return dp[t][x][y];
        dp[t][x][y] = -1;
        int result = Integer.MIN_VALUE;
        if (x + 1 < N) {
            int cost = dfs(x + 1, y, t - 1);
            if (cost != Integer.MIN_VALUE) {
                result = Math.max(result, cost + map[x + 1][y]);
            }
        }
        if (y + 1 < N) {
            int cost = dfs(x, y + 1, t - 1);
            if (cost != Integer.MIN_VALUE) {
                result = Math.max(result, cost + map[x][y + 1]);
            }
        }
        return dp[t][x][y] = result;
    }
}
