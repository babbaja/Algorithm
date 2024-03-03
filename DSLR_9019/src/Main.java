import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            visited = new boolean[10001];
            System.out.println(bfs(num1, num2));
        }
    }

    public static String bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<String> dslr = new LinkedList<>();
        queue.offer(start);
        dslr.offer("");
        visited[start] = true;

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            String str = dslr.poll();
            if (tmp == end) return str;

            int D = (tmp * 2) % 10000;
            if (!visited[D]) {
                visited[D] = true;
                queue.offer(D);
                dslr.offer(str + "D");
            }

            int S = 0;
            if (tmp == 0) S = 9999;
            else S = tmp - 1;
            if (!visited[S]) {
                visited[S] = true;
                queue.offer(S);
                dslr.offer(str + "S");
            }

            int first = tmp / 1000;
            int second = (tmp / 100) % 10;
            int third = (tmp / 10) % 10;
            int forth = tmp % 10;
            int L = second * 1000 + third * 100 + forth * 10 + first;
            if (!visited[L]) {
                visited[L] = true;
                queue.offer(L);
                dslr.offer(str + "L");
            }

            int R = forth * 1000 + first * 100 + second * 10 + third;
            if (!visited[R]) {
                visited[R] = true;
                queue.offer(R);
                dslr.offer(str + "R");
            }

        }
        return "";
    }
}