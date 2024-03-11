import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static char[][] arr;
    public static boolean[][] visited;
    public static int[] dx = new int[] {-1, 0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static Queue<int[]> water = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new boolean[R][C];

        int sx = 0;
        int sy = 0;
        for (int i = 0; i < R; ++i) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < C; ++j) {
                arr[i][j] = list[j];
                if (arr[i][j] == '*') {
                    water.offer(new int[]{i ,j});
                }
                else if (arr[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int ans = bfs(sx, sy);
        System.out.println(ans == -1 ? "KAKTUS" : ans);
    }

    public static int bfs(int x, int y) {
        Queue<int[]> dochi = new LinkedList<>();
        dochi.offer(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!dochi.isEmpty()) {
            int size = water.size();
            int size2 = dochi.size();

            for (int t = 0; t < size; ++t) {
                int[] tmp = water.poll();
                for (int i = 0; i < 4; ++i) {
                    int nx = tmp[0] + dx[i];
                    int ny = tmp[1] + dy[i];

                    if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length) continue;

                    if (arr[nx][ny] == '.') {
                        arr[nx][ny] = '*';
                        water.offer(new int[]{nx, ny});
                    }
                }
            }

            for (int t = 0; t < size2; ++t) {
                int[] tmp2 = dochi.poll();
                if (arr[tmp2[0]][tmp2[1]] == 'D') return tmp2[2];
                for (int i = 0; i < 4; ++i) {
                    int nx2 = tmp2[0] + dx[i];
                    int ny2 = tmp2[1] + dy[i];

                    if (nx2 < 0 || nx2 >= arr.length || ny2 < 0 || ny2 >= arr[0].length) continue;

                    if (arr[nx2][ny2] != '*' && arr[nx2][ny2] != 'X' && !visited[nx2][ny2]) {
                        visited[nx2][ny2] = true;
                        dochi.offer(new int[]{nx2, ny2, tmp2[2] + 1});
                    }
                }
            }
        }

        return -1;
    }
}