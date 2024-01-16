import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int[] xx = {-1, 0, 0, 1};
    public static int[] yy = {0, -1, 1, 0};
    public static int[][] arr;
    public static boolean[][] visited;
    public static ArrayList<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        arr = new int[T][T];
        visited = new boolean[T][T];

        for (int t = 0; t < T; ++t) {
            String str = br.readLine();
            char[] list = str.toCharArray();
            for (int i = 0; i < list.length; ++i) {
                arr[t][i] = list[i] - '0';
                visited[t][i] = false;
            }
        }

        ans = new ArrayList<>();
        for (int i = 0; i < T; ++i) {
            for (int j = 0; j < T; ++j) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(ans.size());
        for (int ans : ans) {
            System.out.println(ans);
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int result = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0 ; i < 4; ++i) {
                int nextX = curr[0] + xx[i];
                int nextY = curr[1] + yy[i];
                if (nextX < 0 || nextX >= arr.length || nextY < 0 || nextY >= arr.length) continue;

                if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    queue.offer(new int[] {nextX, nextY});
                    result += 1;
                    visited[nextX][nextY] = true;
                }
            }
        }
        ans.add(result);
    }
}