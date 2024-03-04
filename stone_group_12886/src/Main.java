import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr = new int[3];
    public static boolean[][] visited = new boolean[1501][1501];;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {arr[0], arr[1], arr[2]});
        visited[arr[0]][arr[1]] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (tmp[0] == tmp[1] && tmp[1] == tmp[2]) return 1;

            if (tmp[0] != tmp[1]) {
                int Y = tmp[0] > tmp[1] ? tmp[0] - tmp[1] : tmp[1] - tmp[0];
                int X = tmp[0] > tmp[1] ? tmp[1] + tmp[1] : tmp[0] + tmp[0];

                if (!visited[Y][X]) {
                    visited[Y][X] = true;
                    queue.offer(new int[]{Y, X, tmp[2]});
                }
            }

            if (tmp[0] != tmp[2]) {
                int Y = tmp[0] > tmp[2] ? tmp[0] - tmp[2] : tmp[2] - tmp[0];
                int X = tmp[0] > tmp[2] ? tmp[2] + tmp[2] : tmp[0] + tmp[0];

                if (!visited[Y][X]) {
                    visited[Y][X] = true;
                    queue.offer(new int[]{Y, X, tmp[1]});
                }
            }

            if (tmp[1] != tmp[2]) {
                int Y = tmp[1] > tmp[2] ? tmp[1] - tmp[2] : tmp[2] - tmp[1];
                int X = tmp[1] > tmp[2] ? tmp[2] + tmp[2] : tmp[1] + tmp[1];

                if (!visited[Y][X]) {
                    visited[Y][X] = true;
                    queue.offer(new int[]{Y, X, tmp[0]});
                }
            }
        }
        return 0;
    }
}