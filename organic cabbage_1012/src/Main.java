import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean[][] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; ++i) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();

            check = new boolean[M][N];
            for(boolean a[] : check)
                Arrays.fill(a,false);

            for (int j = 0; j < K; ++j) {
                int c = sc.nextInt();
                int r = sc.nextInt();
                check[r][c] = true;
            }
            System.out.println(dfsAll(M, N));
        }
        sc.close();
    }

    private static int dfsAll(int M, int N) {
        int compo = 0;
        for(int i = 0; i < M; i++){
            for (int j = 0; j < N; ++j) {
                if(check[i][j]){
                    dfs(i, j);
                    compo++;
                }
            }
        }
        return compo;
    }
    private static void dfs(int M, int N) {
        check[M][N] = false;
        if (M > 0 && check[M - 1][N]) dfs(M - 1, N);
        if (N > 0 && check[M][N - 1]) dfs(M, N - 1);
        if (M < check.length - 1 && check[M + 1][N]) dfs(M + 1, N);
        if (N < check[0].length - 1 && check[M][N + 1]) dfs(M, N + 1);
    }
}