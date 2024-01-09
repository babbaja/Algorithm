import java.util.Scanner;

public class Main {
    static long[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        dp = new long[100000 + 1][4];
        dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
        dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        for (int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
        }
        for (int t = 0; t < num; ++t) {
            int plus_num = sc.nextInt();
            int sum = 0;
            sum += (dp[plus_num][1] + dp[plus_num][2] + dp[plus_num][3]) % 1000000009;
            System.out.println(sum);
        }
    }
}