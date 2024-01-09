import java.util.Scanner;

public class Main {
    static int[] dp;
    static int[] dp2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        dp = new int[num + 1];
        dp2 = new int[num + 1];

        for (int i = 1; i <= num; ++i)
            dp[i] = sc.nextInt();

        for (int i = 1; i <= num; ++i) {
            for (int j = 1; j <= i; j++) {
                if (dp2[i] == 0)
                    dp2[i] = Math.max(dp2[i], dp2[i - j] + dp[j]);
                else
                    dp2[i] = Math.min(dp2[i], dp2[i - j] + dp[j]);
            }
        }
        System.out.println(dp2[num]);
    }
}