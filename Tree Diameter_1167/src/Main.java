import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
    int to;
    int diameter;
    public Node(int to, int diameter) {
        this.to = to;
        this.diameter = diameter;
    }
}

public class Main {
    public static ArrayList<ArrayList<Node>> tree;
    public static boolean[] visited;
    public static int max = 0;
    public static int temp = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < N + 1; ++i) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1) break;
                int value = Integer.parseInt(st.nextToken());
                tree.get(index).add(new Node(node, value));
            }
        }

        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(temp, 0);

        System.out.println(max);
    }


    public static void dfs(int start, int value) {
        if (max < value) {
            max = value;
            temp = start;
        }
        visited[start] = true;
        for (Node node : tree.get(start)) {
            if (!visited[node.to]) {
                dfs(node.to, value + node.diameter);
            }
        }
    }
}