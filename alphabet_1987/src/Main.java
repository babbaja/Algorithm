import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] list;
    static boolean[] alpa = new boolean[26];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new int[R][C];

        for (int i = 0; i < R; ++i) {
            String str = br.readLine();
            for (int j = 0; j < C; ++j) {
                list[i][j] = str.charAt(j) - 'A';
            }
        }
        alphabet(0, 0, 0);
        System.out.println(ans);
    }

    static void alphabet(int cnt, int row, int cols) {
        if (alpa[list[row][cols]]) {
            ans = Math.max(ans, cnt);
            return;
        }
        else {
            alpa[list[row][cols]] = true;
            if (row > 0) alphabet(cnt + 1, row - 1, cols);
            if (cols > 0) alphabet(cnt + 1, row, cols - 1);
            if (row < R - 1) alphabet(cnt + 1, row + 1, cols);
            if (cols < C - 1) alphabet(cnt + 1, row, cols + 1);
            alpa[list[row][cols]] = false;
        }
    }
}