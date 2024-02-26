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

    public static boolean check(int depth) {
        for (int i = 0; i < depth; ++i) {
            if (chess[depth] == chess[i]) return false;
            else if (Math.abs(depth - i) == Math.abs(chess[depth] - chess[i])) return false;
        }
        return true;
    }
}