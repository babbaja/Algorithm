import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int firstX;
    public static int firstY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            char[] list = str.toCharArray();
            for (int j = 0; j < M; ++j) {
                arr[i][j] = list[j] - 'A';
            }
        }

        boolean ans = false;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                visited = new boolean[N][M];
                firstX = j;
                firstY = i;
                ans = dfs(j, i, arr[j][i], 0);
                if (ans) break;
            }
            if (ans) break;
        }

        System.out.println(ans ? "Yes" : "No");
    }

    public static boolean dfs(int x, int y, int color, int depth) {
        depth += 1;
        visited[x][y] = true;

        for (int i = 0; i < 4; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;

            if (depth >= 4 && firstY == nextY && firstX == nextX && arr[nextX][nextY] == color && visited[nextX][nextY]) return true;
            if (!visited[nextX][nextY] && arr[nextX][nextY] == color) {
                if (dfs(nextX, nextY, color, depth)) return true;
            }
        }
        return false;
    }
}