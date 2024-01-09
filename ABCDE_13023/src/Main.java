import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Integer>[] arr;
    public static boolean[] visited;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N];
        visited = new boolean[N];

        for(int i = 0; i < N; ++i) {
            arr[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1].add(n2);
            arr[n2].add(n1);
        }
        for(int i = 0; i < N; ++i) {
            if(ans == 0)
                abcde(i, 1);
        }
        System.out.println(ans);
        br.close();
    }

    public static void abcde(int index, int depth) {
        System.out.println(index + " " + depth);
        if(depth == 5) {
            ans = 1;
            System.out.println(5);
            return;
        }

        visited[index] = true;
        for(int next : arr[index]) {
            if(!visited[next]) {
                abcde(next, depth + 1);
            }
        }
        visited[index] = false;
        
    }
}