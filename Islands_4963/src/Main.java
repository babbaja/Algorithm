import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int w = 0;
        int h = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        do {
            arr = new int[h][w];
            visited = new boolean[h][w];
            int island = 0;
            for (int i = 0; i < h; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; ++j) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = false;
                }
            }

            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                        island += 1;
                    }
                }
            }
            System.out.println(island);
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
        } while (w != 0 || h != 0);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 8; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;

            if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                dfs(nextX, nextY);
            }
        }
    }
}