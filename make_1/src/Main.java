import java.util.Scanner;

public class Main {
    static Integer[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        dp = new Integer[num + 1];
        dp[0] = dp[1] = 0;

        System.out.println(recur(num));
    }

    static int recur(int num) {
        if (dp[num] == null) {
            if (num % 6 == 0) { // 6으로 나눠질때
                dp[num] = Math.min(recur(num - 1), Math.min(recur(num / 3), recur(num / 2))) + 1;
            }
            else if (num % 3 == 0) { // 3으로 나눠질때
                dp[num] = Math.min(recur(num / 3), recur(num - 1)) + 1;
            }
            else if (num % 2 == 0) { // 2로 나눠질떄
                dp[num] = Math.min(recur(num / 2), recur(num - 1)) + 1;
            }
            else { // 6, 2, 3으로 나눠지지 않을 때
                dp[num] = recur(num - 1) + 1;
            }
        }
        return dp[num];
    }
}