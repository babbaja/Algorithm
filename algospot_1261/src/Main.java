import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; ++i) {
            String str = br.readLine();
            char[] list = str.toCharArray();
            for (int j = 0; j < N; ++j) {
                arr[i][j] = list[j] - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        visited[x][y] = true;
        queue.offer(new int[]{x, y, 0});
        if (arr.length == 1 && arr[0].length == 1) {
            return arr[x][y];
        }

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr[0].length) continue;
                if (visited[nextX][nextY]) continue;

                if (nextX == arr.length - 1 && nextY == arr[0].length - 1) {
                    return tmp[2];
                }

                if (arr[nextX][nextY] == 1) {
                    queue.offer(new int[]{nextX, nextY, tmp[2] + 1});
                }
                else {
                    queue.offer(new int[]{nextX, nextY, tmp[2]});
                }
                visited[nextX][nextY] = true;
            }
        }
        return 0;
    }
}