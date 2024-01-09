import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean visited[];
    static int N;
    static int two;
    static int soluton = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        visited = new boolean[N + 1];

        int first = Integer.parseInt(st.nextToken());
        two = Integer.parseInt(st.nextToken());

        int roop = Integer.parseInt(br.readLine());

        for (int i = 0; i < roop ; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int count = 0;
        dfs(first,count);

        System.out.println(soluton != 0 ? soluton : -1);
    }

    static void dfs(int index, int count){

        visited[index] = true;
        System.out.println(index + "  " + graph.get(index));
        for (int temp : graph.get(index)){
            if(!visited[temp]){
                if(two == temp){
                    soluton = count + 1;
                    return;
                }
                dfs(temp,count + 1);
            }
        }
    }

    private static void BFS(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == end) break;

            for (int i = 1; i <= N; i++) {
                if (graph.get(v).get(i) == 1 && d[i] == 0) {
                    d[i] = d[v] + 1;
                    q.add(i);
                }
            }
        }
    }

}