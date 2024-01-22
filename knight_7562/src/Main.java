import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
    public static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[][] arr;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            int l = Integer.parseInt(br.readLine());
            arr = new int[l][l];
            visited = new boolean[l][l];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            bfs(x1, y1);

            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            System.out.println(arr[x2][y2]);
        }

    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        arr[x][y] = 0;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 8; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];
                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr.length) continue;

                if (!visited[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = arr[tmp[0]][tmp[1]] + 1;
                }
            }

        }

    }
}