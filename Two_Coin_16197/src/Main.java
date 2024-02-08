import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static char[][] arr;
    public static boolean[][][][] visited;
    public static ArrayList<int[]> coins;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        coins = new ArrayList<>();
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; ++i) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < M; ++j) {
                arr[i][j] = list[j];
                if (arr[i][j] == 'o') coins.add(new int[]{i, j});
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int[] coin1 = coins.get(0);
        int[] coin2 = coins.get(1);
        queue.offer(new int[] {coin1[0], coin1[1], coin2[0], coin2[1], 0});
        visited[coin1[0]][coin1[1]][coin2[0]][coin2[1]] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (tmp[4] >= 10) break;
            for (int i = 0; i < 4; ++i) {
                int nx1 = tmp[0] + dx[i];
                int ny1 = tmp[1] + dy[i];
                int nx2 = tmp[2] + dx[i];
                int ny2 = tmp[3] + dy[i];

                if (!coinmove(nx1, ny1)) {
                    nx1 = tmp[0];
                    ny1 = tmp[1];
                }
                if (!coinmove(nx2, ny2)) {
                    nx2 = tmp[2];
                    ny2 = tmp[3];
                }

                int cnt = 0;
                if(nx1 >= 0 && ny1 >= 0 && nx1 < arr.length && ny1 < arr[0].length) cnt++;
                if(nx2 >= 0 && ny2 >= 0 && nx2 < arr.length && ny2 < arr[0].length) cnt++;
                if (cnt == 2 && !visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    queue.offer(new int[] {nx1, ny1, nx2, ny2, tmp[4] + 1});
                }
                else if (cnt == 1) {
                    return tmp[4] + 1;
                }
            }
        }
        return -1;
    }

    public static boolean coinmove(int x ,int y) {
        return x < 0 || x >= arr.length || y < 0 || y >= arr[0].length || arr[x][y] != '#';
    }
}