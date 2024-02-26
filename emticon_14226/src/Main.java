import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        visited = new boolean[S + 1][S + 1];

        System.out.println(bfs(S));
    }

    public static int bfs(int end) {
        Queue<int[]> queue = new LinkedList<>();
        int display = 1;
        int clipboard = 0;
        queue.offer(new int[]{1, 0, 0});
        visited[display][clipboard] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            if (tmp[0] == end) return tmp[2];

            //copy
            clipboard = tmp[0];
            queue.offer(new int[]{tmp[0], clipboard, tmp[2] + 1});

            //paste
            if (tmp[1] != 0 && tmp[0] + tmp[1] < visited.length && !visited[tmp[0]][tmp[0] + tmp[1]]) {
                display = tmp[0] + tmp[1];
                visited[tmp[0]][display] = true;
                queue.offer(new int[]{display, tmp[1], tmp[2] + 1});
            }

            //delete
            if (tmp[0] - 1 > 0 && !visited[tmp[0] - 1][tmp[1]]) {
                display = tmp[0] - 1;
                visited[display][tmp[1]] = true;
                queue.offer(new int[]{display, tmp[1], tmp[2] + 1});
            }
        }
        return 0;
    }
}