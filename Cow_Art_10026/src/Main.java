import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static char[][] arr;
    public static int[] dx = new int[] {-1, 0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            char[] list = br.readLine().toCharArray();
            arr[i] = list;
        }
        int ans1 = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j){
                if (!visited[i][j]) {
                    bfs(i, j);
                    ans1++;
                }
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] == 'G') arr[i][j] = 'R';
            }
        }
        int ans2 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j){
                if (!visited[i][j]) {
                    bfs(i, j);
                    ans2++;
                }
            }
        }


        System.out.println(ans1 + " " + ans2);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length || visited[nx][ny]) continue;

                if (arr[tmp[0]][tmp[1]] == arr[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

}