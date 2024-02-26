import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] chess;
    public static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        chess = new int[N];

        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int depth) {
        if (depth == chess.length) {
            ans++;
            return;
        }

        for (int i = 0; i < chess.length; ++i) {
            chess[depth] = i;

            if (check(depth)) {
                dfs(depth + 1);
            }
        }
    }

    public static boolean check(int index) {
        for (int i = 0; i < index; ++i) {
            if (chess[index] == chess[i]) return false;
            else if (Math.abs(i - index) == Math.abs(chess[i] - chess[index])) return false;
        }
        return true;
    }
}
