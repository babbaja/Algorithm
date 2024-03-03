import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[][] arr1;
    public static int[] dx = new int[] {-1 ,0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static int ans = 0;
    public static ArrayList<int[]> virus;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        virus = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    virus.add(new int[] {i, j});
                }
            }
        }
        wall(0);
        System.out.println(ans);
        /*
        System.out.println();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }

         */
    }

    public static void wall(int cnt) {
        if (cnt == 3) {
            arr1 = new int[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, arr1[i], 0, arr[i].length);
            }
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    wall(cnt + 1);
                    arr[i][j] = 0;
                }
            }
        }

    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] two : virus) {
            queue.offer(new int[]{two[0], two[1]});
        }
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= arr1.length || ny < 0 || ny >= arr1[0].length) continue;

                if (arr1[nx][ny] == 0) {
                    arr1[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int zero = 0;
        for (int i = 0; i < arr1.length; ++i) {
            for (int j = 0; j < arr1[0].length; ++j) {
                if (arr1[i][j] == 0) {
                    ++zero;
                }
            }
        }
        return zero;
    }
}