import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr = new int[101];
    public static boolean[] visited = new boolean[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; ++i) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            arr[num1] = num2;
        }

        System.out.println(bfs(1));
    }

    public static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 1; i <= 6; ++i) {
                int num = tmp[0] + i;
                if (visited[num]) continue;
                visited[num] = true;
                if (arr[num] != 0) {
                    num = arr[num];
                    visited[num] = true;
                }
                if (num > 100) continue;
                if (num == 100) return tmp[1] + 1;
                queue.offer(new int[]{num, tmp[1] + 1});
            }
        }
        return 0;
    }
}