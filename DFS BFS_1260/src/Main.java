import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] arr;
    public static boolean[] Dvisited;
    public static boolean[] Bvisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        Dvisited = new boolean[N + 1];
        Bvisited = new boolean[N + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        for (int i = 0; i < arr.length; ++i) {
            Collections.sort(arr[i]);
        }

        dfs(V);
        System.out.println();
        bfs(V);

        br.close();
    }

    public static void dfs(int index) {
        System.out.printf(index + " ");
        Dvisited[index] = true;
        for(int next : arr[index]) {
            if(!Dvisited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);
        Bvisited[index] = true;
        while(!queue.isEmpty()) {
            int tmp = queue.poll();

            System.out.printf(tmp + " ");
            for (int next : arr[tmp]) {
                if(!Bvisited[next]) {
                    queue.offer(next);
                    Bvisited[next] = true;
                }
            }
        }
    }
}