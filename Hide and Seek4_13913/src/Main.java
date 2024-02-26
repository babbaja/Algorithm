import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> arr;
    public static ArrayList<Integer> ans;
    static StringBuilder sequen = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        visited = new boolean[N + K + 2];
        arr = new ArrayList<>();
        ans = new ArrayList<>();
        for (int i = 0 ; i <  N + K + 2; ++i) {
            arr.add(new ArrayList<>());
        }

        System.out.println(bfs(N, K));

        path(K);
        /*
        for (int i = ans.size() - 1; i >= 0; --i) {
            System.out.printf("%d ", ans.get(i));
        }
        System.out.println();

         */
        System.out.println(sequen);

    }

    public static int bfs(int start, int end) {
        if (start == end) return 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int minus = tmp[0] - 1;
            int plus = tmp[0] + 1;
            int multi = tmp[0] * 2;


            if (minus >= 0 && !visited[minus]) {
                queue.offer(new int[]{minus, tmp[1] + 1});
                visited[minus] = true;
                arr.get(minus).add(tmp[0]);
            }
            if (plus < visited.length && !visited[plus]) {
                queue.offer(new int[]{plus, tmp[1] + 1});
                visited[plus] = true;
                arr.get(plus).add(tmp[0]);
            }
            if (multi < visited.length && !visited[multi]) {
                queue.offer(new int[]{multi, tmp[1] + 1});
                visited[multi] = true;
                arr.get(multi).add(tmp[0]);
            }
            if (minus == end || plus == end || multi == end) {
                return tmp[1] + 1;
            }
        }
        return 0;
    }

    public static void path(int index) {
        //ans.add(index);
        sequen.insert(0, index + " ");
        for (int next : arr.get(index)) {
            path(next);
        }
    }
}