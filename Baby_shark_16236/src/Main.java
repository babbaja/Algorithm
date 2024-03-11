import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[] dx = new int[] {-1, 0, 0, 1};
    public static int[] dy = new int[] {0, -1, 1, 0};
    public static int shark = 2;
    public static int eat = 0;
    public static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        int bx = 0;
        int by = 0;
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    bx = i;
                    by = j;
                }
            }
        }

        while (true) {
            int[] food = bfs(bx, by);
            if (food[0] == -1 && food[1] == -1) {
                break;
            }
            else {
                time += food[2];
                bx = food[0];
                by = food[1];
            }

            if (eat == shark) {
                ++shark;
                eat = 0;
            }
        }
        System.out.println(time);
    }

    public static int[] bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        visited[x][y] = true;
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int mindistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length || arr[nx][ny] > shark || visited[nx][ny]) continue;

                if (arr[nx][ny] < shark && arr[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    if (mindistance >= tmp[2]) {
                        if (minx > nx) {
                            minx = nx;
                            miny = ny;
                            mindistance = tmp[2];
                        }
                        else if (minx == nx) {
                            if (miny > ny) {
                                miny = ny;
                                mindistance = tmp[2];
                            }
                        }
                    }
                }
                else if (arr[nx][ny] == shark || arr[nx][ny] == 0){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, tmp[2] + 1});
                }
            }
        }
        if (minx == Integer.MAX_VALUE) return new int[] {-1, -1};
        else {
            arr[x][y] = 0;
            arr[minx][miny] = 9;
            ++eat;
            return new int[]{minx, miny, mindistance + 1};
        }
    }
}