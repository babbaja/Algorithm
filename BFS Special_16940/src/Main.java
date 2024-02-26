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
        else System.out.println(bfs(1) ? 1 : 0);

    }

    public static boolean bfs(int index) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> hash = new HashSet<>();
        queue.offer(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int next : arr.get(tmp)) {
                if (!visited[next]) {
                    visited[next] = true;
                    hash.add(next);
                }
            }

            int size = hash.size();
            if (!input.isEmpty()) {
                for (int i = 0; i < size; ++i) {
                    int num = input.poll();
                    if (hash.contains(num)) {
                        queue.offer(num);
                    }
                    else return false;
                }
            }
            hash.clear();
        }
        return true;
    }
}