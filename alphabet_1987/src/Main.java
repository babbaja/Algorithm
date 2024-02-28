import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[] dx = new int[] {-1 ,0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static boolean[] alpha = new boolean[26];
    public static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for (int i = 0; i < R; ++i) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < C; ++j) {
                arr[i][j] = list[j] - 'A';
            }
        }
        alpha[arr[0][0]] = true;
        alphabet(0, 0, 1);
        System.out.println(ans);
    }

    public static void alphabet(int x, int y, int cnt) {
        ans = Math.max(ans, cnt);

        for (int i = 0; i < 4; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextX < arr.length && nextY >= 0 && nextY < arr[0].length && !alpha[arr[nextX][nextY]]) {
                alpha[arr[nextX][nextY]] = true;
                alphabet(nextX, nextY, cnt + 1);
                alpha[arr[nextX][nextY]] = false;
            }
        }
    }
}