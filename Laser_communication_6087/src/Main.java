import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static char[][] arr;
    public static int[][][] visited;
    public static int[] dx = new int[] {-1, 0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new char[M][W];
        visited = new int[M][W][4];

        int[] c = new int[4];
        int idx = 0;
        for (int i = 0; i < M; ++i) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < W; ++j) {
                arr[i][j] = list[j];
                if (list[j] == 'C') {
                    c[idx] = i;
                    c[idx + 1] = j;
                    idx += 2;
                }
            }
        }

        System.out.println(bfs(c[0], c[1], c[2], c[3]));
    }

    public static int bfs(int x, int y, int destx, int desty) { //방향 상 좌 하 우 0 1 2 3
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, -1, 0}); // x, y, 방향, 거울
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            if (tmp[0] == destx && tmp[1] == desty) {
                min = Math.min(min, tmp[3]);
                continue;
            }

            for (int i = 0; i < 4; ++i) { // 상 좌 하 우 0 1 2 3
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length || arr[nx][ny] == '*') continue;

                if (visited[nx][ny][i] > tmp[3]) {
                    if (tmp[2] != i) {
                        if (tmp[2] == -1) queue.offer(new int[]{nx, ny, i, tmp[3]});
                        else queue.offer(new int[]{nx, ny, i, tmp[3] + 1});
                        visited[nx][ny][i] = tmp[3] + 1;
                    }
                    else {
                        queue.offer(new int[]{nx, ny, i, tmp[3]});
                        visited[nx][ny][i] = tmp[3];
                    }
                }
            }
        }
        return min;
    }

}