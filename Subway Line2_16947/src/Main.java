import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<ArrayList<Integer>> arr;
    public static boolean[] circle;
    public static boolean[] visited;
    public static int first;
    public static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        ans = new int[T + 1];
        visited = new boolean[T + 1];
        for (int i = 0; i < T + 1; ++i) {
            arr.add(new ArrayList<Integer>());
            ans[i] = -1;
        }

        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr.get(n1).add(n2);
            arr.get(n2).add(n1);
        }

        for (int i = 1; i < T; ++i) {
            circle = new boolean[T + 1];
            first = i;
            if (dfs(i, 0)) break;
        }

        for (int i = 1; i < T + 1; ++i) {
            if (circle[i]) {
                ans[i] = 0;
                distance(i);
            }
        }

        for (int i = 1; i < T + 1; ++i) {
            System.out.printf("%d ", ans[i]);
        }
    }

    public static boolean dfs(int index, int depth) {
        depth += 1;
        circle[index] = true;
        if (arr.get(index).contains(first) && circle[first] && depth >= 3) {
            ans[index] = 0;
            return true;
        }

        for (int next : arr.get(index)) {
            if (!circle[next]) {
                circle[next] = true;
                if (dfs(next, depth)) {
                    ans[index] = 0;
                    return true;
                }
            }
        }
        circle[index] = false;
        return false;
    }

    public static void distance(int index) {
        visited[index] = true;
        for (int next : arr.get(index)) {
            if (ans[next] != 0 && !visited[next]) {
                ans[next] = ans[index] + 1;
                distance(next);
            }
        }
    }
}