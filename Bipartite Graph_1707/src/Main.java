import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<ArrayList<Integer>> arr;
    public static int[] colors;
    public static int White = 1;
    public static int Black = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            arr = new ArrayList<>();
            colors = new int[V + 1];

            for (int i = 0; i < V + 1; ++i) {
                arr.add(new ArrayList<Integer>());
                colors[i] = 0;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                arr.get(u).add(v);
                arr.get(v).add(u);
            }

            System.out.println(isBipartite(V) ? "YES" : "NO");
        }
    }

    public static boolean isBipartite(int V) {
        for (int i = 1; i < V + 1; ++i) {
            if (colors[i] == 0) {
                if (!bfs(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = White;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : arr.get(curr)) {
                if (colors[next] == 0) {
                    queue.offer(next);
                    colors[next] = -colors[curr];
                }
                else {
                    if (colors[next] == colors[curr]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
