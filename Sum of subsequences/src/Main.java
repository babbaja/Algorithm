import java.util.Scanner;

public class Main {
    static int[] arr;
    private static int num;
    private static int S;
    private static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        S = sc.nextInt();
        arr = new int[num];

        for (int i = 0 ; i < num; ++i) arr[i] = sc.nextInt();

        dfs(0,0);
        if (S == 0) System.out.println(ans - 1);
        else System.out.println(ans);
    }

    private static void dfs(int depth, int sum) {
       if (depth == num) {
           if (sum == S) ans++;
           return;
       }
       dfs(depth + 1, sum + arr[depth] );
       dfs(depth + 1, sum);
    }
}