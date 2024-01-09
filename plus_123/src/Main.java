//bottom-up
import java.util.Scanner;

public class Main {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for (int i = 0; i < num; ++i) {

            int plus_num = sc.nextInt();
            dp = new int[plus_num + 2];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int j = 3; j <= plus_num; j++) {
                dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
            }
            System.out.println(dp[plus_num]);
        }
    }
}