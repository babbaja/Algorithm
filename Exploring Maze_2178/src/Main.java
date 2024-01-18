import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[] dx = { -1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int t = 0; t < N; ++t) {
            String str = br.readLine();
            char[] list = str.toCharArray();
            for (int i = 0; i < list.length; ++i) {
                arr[t][i] = list[i] - '0';
                visited[t][i] = false;
            }
        }
        bfs(0,0);
        System.out.println(arr[N - 1][M - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;
                if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = arr[tmp[0]][tmp[1]] + 1;
                }
            }
        }
    }
}