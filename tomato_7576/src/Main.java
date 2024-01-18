import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        boolean ans = true;
        int days = bfs();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] != -1 && !visited[i][j]) {
                    ans = false;
                    break;
                }
            }
        }

        System.out.println(ans ? days : -1);
    }

    public static int bfs() {
        int max = 0;
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;
                if (arr[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = arr[tmp[0]][tmp[1]] + 1;
                    max = Math.max(arr[nextX][nextY], max);
                }
            }
        }
        return max == 0 ? 0 : max - 1;
    }
}