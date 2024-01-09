import java.util.Scanner;

public class Main {
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        dp = new int[num + 1];

        System.out.print(make_2(num));
    }
    static int make_2(int num) {
        if (num <= 2) {
            return dp[num] = num;
        }
        else if (dp[num] != 0) {
            return dp[num];
        }
        else {
            dp[num] =  (make_2(num - 1) + make_2(num - 2)) % 10007;
            return dp[num];
        }
    }
}