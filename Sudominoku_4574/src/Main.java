import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[] dx = new int[]{0, 1};
    public static int[] dy = new int[]{1, 0};
    public static boolean flag;
    public static int cnt = 1;
    public static boolean[][] block;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            block = new boolean[10][10];
            arr = new int[9][9];
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                char[] list = st.nextToken().toCharArray();
                arr[list[0] - 'A'][list[1] - '1'] = num1;
                int num2 = Integer.parseInt(st.nextToken());
                list = st.nextToken().toCharArray();
                arr[list[0] - 'A'][list[1] - '1'] = num2;
                block[num1][num2] = true;
                block[num2][num1] = true;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 10; ++i) {
                char[] list = st.nextToken().toCharArray();
                arr[list[0] - 'A'][list[1] - '1'] = i;
            }

            flag = false;
            sudominoku(0, 0);
            cnt++;
        }
    }

    public static void sudominoku(int x, int y) {
        if (y == 9) {
            sudominoku(x + 1, 0);
            return;
        }
        if (x == 9) {
            if (flag) return;
            flag = true;
            System.out.printf("Puzzle %d\n", cnt);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            return;
        }

        if (arr[x][y] != 0) {
            sudominoku(x, y + 1);
        }

        if (arr[x][y] == 0) {
            for (int k = 0; k < 2; ++k) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if(nextX < 0 ||  nextY < 0 || nextX >= 9 || nextY >= 9 || arr[nextX][nextY] != 0) continue;

                for (int i = 1; i < 10; ++i) {
                    for (int j = 1; j < 10; ++j) {
                        if (i == j) continue;

                        if (check(x, y, i) && check(nextX, nextY, j) && !block[i][j]) {
                            arr[x][y] = i;
                            arr[nextX][nextY] = j;
                            block[i][j] = true;
                            block[j][i] = true;

                            sudominoku(x, y + 1);

                            arr[x][y] = 0;
                            arr[nextX][nextY] = 0;
                            block[i][j] = false;
                            block[j][i] = false;
                        }
                    }
                }
            }
        }
    }

    public static boolean check(int x, int y, int value) {
        for (int i = 0; i < 9; ++i) {
            if (arr[x][i] == value) return false;
            if (arr[i][y] == value) return false;
        }

        int sx = (x / 3) * 3;
        int sy = (y / 3) * 3;
        for (int i = sx; i < sx + 3; ++i) {
            for (int j = sy; j < sy + 3; ++j) {
                if (arr[i][j] == value) return false;
            }
        }
        return true;
    }
}