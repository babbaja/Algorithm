import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[] list;
    static boolean[][] failed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        list = br.readLine().toCharArray();

        if (n % 2 == 1) {
            System.out.println("No");
            return;
        }

        failed = new boolean[n + 1][n + 1];

        System.out.println(dfs(0, 0, 0) ? "Yes" : "No");
    }

    static boolean dfs(int s, int in, int out) {
        int balance = in - out;

        if (balance < 0) return false;
        if (balance > n - s) return false;
        if (s == n) return balance == 0;

        if (failed[s][balance]) return false;

        char ch = list[s];
        boolean result = false;

        if (ch == '(') {
            result = dfs(s + 1, in + 1, out);
        } 
        else if (ch == ')') {
            result = dfs(s + 1, in, out + 1);
        } 
        else {
            result = dfs(s + 1, in + 1, out);

            if (!result && balance > 0) {
                result = dfs(s + 1, in, out + 1);
            }
        }

        if (!result) {
            failed[s][balance] = true;
        }

        return result;
    }
}