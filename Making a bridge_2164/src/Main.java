import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[][] ans;
    public static boolean[][] visited;
    public static boolean[][] visited2;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0 ,-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        visited = new boolean[N][N];
        ans = new int[N][N];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int island = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, island);
                    island += 1;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (arr[i][j] > 0 ) {
                    visited2 = new boolean[N][N];
                    int ans = bfs2(i, j);
                    if (min > ans && ans != 0) {
                        min = ans;
                    }
                }
            }
        }
        System.out.println(min);

    }

    public static void bfs(int x, int y, int island) {
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        arr[x][y] = island;

        while(!queue.isEmpty()) {
            int tmp[] = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr.length) continue;
                if (!visited[nextX][nextY] && arr[nextX][nextY] == 1) {
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = island;
                }
            }
        }
    }

    public static int  bfs2(int x, int y) {
        int bridge = 0;
        visited2[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, bridge});


        while(!queue.isEmpty()) {
            int tmp[] = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nextX = tmp[0] + dx[i];
                int nextY = tmp[1] + dy[i];
                int distance = tmp[2];

                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr.length) continue;
                if (!visited2[nextX][nextY] && arr[nextX][nextY] != arr[x][y]) {
                    queue.offer(new int[] {nextX, nextY, distance + 1});
                    visited2[nextX][nextY] = true;

                }
                if (arr[nextX][nextY] != arr[x][y] && visited[nextX][nextY]) {
                    return distance;
                }
            }
        }
       return bridge;
    }
}