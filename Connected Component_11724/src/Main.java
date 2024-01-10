import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Integer>[] arr;
    public static boolean[] visited;
    public static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        ans = N;
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        for (int i = 1; i < N + 1; ++i) {
            if (!visited[i]) {
                ans += 1;
                Connected(i);
            }
        }

        System.out.println(ans);
    }

    public static void Connected(int index) {
        ans -= 1;
        visited[index] = true;
        for(int next : arr[index]) {
            if(!visited[next]) {
                Connected(next);
            }
        }
    }
}