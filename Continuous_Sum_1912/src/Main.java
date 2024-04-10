import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(continuous(arr));
    }

    public static int continuous(int[] arr) {
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < arr.length; ++i) {
            int sum = dp[i - 1] + arr[i];
            dp[i] = Math.max(sum, arr[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }
}