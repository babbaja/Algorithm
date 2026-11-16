import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int T;
    static int[][] map;
    static int[] air = new int[2];

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        int idx = 0;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    air[idx++] = i;
                }
            }
        }

        solve();
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    static void solve() {
        while (T-- > 0) {
            spread();
            clean();
        }
    }

    static void spread() {
        int[][] next = new int[r][c];

        next[air[0]][0] = -1;
        next[air[1]][0] = -1;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (map[x][y] <= 0) continue;

                int amount = map[x][y] / 5;
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                        continue;
                    }

                    if (map[nx][ny] == -1) {
                        continue;
                    }

                    next[nx][ny] += amount;
                    count++;
                }

                next[x][y] += map[x][y] - amount * count;
            }
        }

        map = next;
    }

    static void clean() {
        int upper = air[0];
        int lower = air[1];

        for (int i = upper - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        for (int j = 0; j < c - 1; j++) {
            map[0][j] = map[0][j + 1];
        }

        for (int i = 0; i < upper; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }

        for (int j = c - 1; j > 1; j--) {
            map[upper][j] = map[upper][j - 1];
        }

        map[upper][1] = 0;

        for (int i = lower + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        for (int j = 0; j < c - 1; j++) {
            map[r - 1][j] = map[r - 1][j + 1];
        }

        for (int i = r - 1; i > lower; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }

        for (int j = c - 1; j > 1; j--) {
            map[lower][j] = map[lower][j - 1];
        }

        map[lower][1] = 0;

        map[upper][0] = -1;
        map[lower][0] = -1;
    }
}