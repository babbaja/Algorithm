import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> arr;
    public static boolean[] visited;
    public static Queue<Integer> input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        for (int i = 0; i < N + 1; ++i) {
            arr.add(new ArrayList<Integer>());
        }
        visited = new boolean[N + 1];
        for (int t = 0; t < N - 1; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr.get(n1).add(n2);
            arr.get(n2).add(n1);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            input.offer(Integer.parseInt(st.nextToken()));
        }
        int first = input.poll();
        if (first != 1) System.out.println(0);
        else System.out.println(dfs(1) ? 1 : 0);
    }

    public static boolean dfs(int index) {
        HashSet<Integer> hash = new HashSet<>();
        visited[index] = true;

        for (int next : arr.get(index)) {
            if (!visited[next]) {
                hash.add(next);
            }
        }

        while (!input.isEmpty()) {
            int num = input.peek();
            if (hash.contains(num)) {
                hash.remove(num);
                input.poll();
                dfs(num);
            }
            else return false;
        }
        return true;
    }
}