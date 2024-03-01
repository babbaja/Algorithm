import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = new int[] {-2, -2, 0, 0, 2, 2};
    public static int[] dy = new int[] {-1, 1, -2, 2, -1, 1};
    public static int r2, c2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(r1, c1));
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y, 0});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 6; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr.length || visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;
                if (nextX == r2 && nextY == c2) {
                    return tmp[2] + 1;
                }
                queue.offer(new int[] {nextX, nextY, tmp[2] + 1});
            }
        }
        return -1;
    }
}