import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<ArrayList<Integer>> tree;
    public static int[] parent;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; ++i) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            tree.get(num1).add(num2);
            tree.get(num2).add(num1);
        }

        findparent(1);

        for (int i = 2; i < parent.length; ++i) {
            System.out.println(parent[i]);
        }
    }

    public static void findparent(int index) {
        visited[index] = true;

        for (int next : tree.get(index)) {
            if (!visited[next]) {
                parent[next] = index;
                findparent(next);
            }
        }
    }
}