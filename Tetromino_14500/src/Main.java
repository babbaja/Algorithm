import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                visited[i][j] = true;
                tetrominoSum(i, j, 0, 0);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }

    public static void tetrominoSum(int x, int y, int depth, int value) {
        if (depth == 4) {
            max = Math.max(max, value);
            return;
        }
        for (int i = 0; i < 4; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;
            if (!visited[nextX][nextY]) {
                if (depth == 2) {
                    visited[nextX][nextY] = true;
                    tetrominoSum(x, y, depth + 1, value + arr[nextX][nextY]);
                    visited[nextX][nextY] = false;
                }
                visited[nextX][nextY] = true;
                tetrominoSum(nextX, nextY, depth + 1, value + arr[nextX][nextY]);
                visited[nextX][nextY] = false;
            }
        }

    }
}