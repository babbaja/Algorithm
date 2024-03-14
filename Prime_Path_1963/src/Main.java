import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = new int[] {1000, 100, 10, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int ans = prime(start, end);
            System.out.println(ans == -1 ? "Impossible" : ans);
        }
    }

    public static int prime(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, end, 0});
        boolean[] visited = new boolean[10000];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (tmp[0] == end) return tmp[2];
            for (int i = 0; i < 4; ++i) { // 1033 1733 3733 3739 3779 8779 8179
                int digit = tmp[0] / dx[i] % 10;
                int num = tmp[0] - digit * dx[i];

                for (int j = 0; j < 10; ++j) {
                    if (i == 0 && j == 0) continue;
                    else if (digit == j) continue;
                    int nextN = j * dx[i] + num;
                    if (!visited[nextN])
                        if (isPrime(nextN)) {
                            visited[nextN] = true;
                            queue.offer(new int[]{nextN, tmp[1], tmp[2] + 1});
                        }
                }
            }
        }

        return -1;
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= (int)Math.sqrt(num); ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}