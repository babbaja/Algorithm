import java.util.*;

public class Main {
    static int[][] capacity = new int[52][52];
    static int[][] flow = new int[52][52];
    static List<Integer>[] adj = new ArrayList[52];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 0; i < 52; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int u = charToInt(sc.next().charAt(0));
            int v = charToInt(sc.next().charAt(0));
            int w = sc.nextInt();
            
            capacity[u][v] += w;
            capacity[v][u] += w; // 양방향
            adj[u].add(v);
            adj[v].add(u);
        }

        System.out.println(maxFlow(charToInt('A'), charToInt('Z')));
    }

    static int maxFlow(int source, int sink) {
        int totalFlow = 0;

        while (true) {
            int[] parent = new int[52];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();

            q.add(source);
            parent[source] = source;

            while (!q.isEmpty() && parent[sink] == -1) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    // 잔여 용량이 남아있고 방문하지 않은 노드라면
                    if (capacity[cur][next] - flow[cur][next] > 0 && parent[next] == -1) {
                        q.add(next);
                        parent[next] = cur;
                    }
                }
            }

            // 더 이상 경로가 없으면 종료
            if (parent[sink] == -1) break;

            // 경로상 최소 잔여 용량 찾기
            int amount = Integer.MAX_VALUE;
            for (int i = sink; i != source; i = parent[i]) {
                amount = Math.min(amount, capacity[parent[i]][i] - flow[parent[i]][i]);
            }

            // 흐름 업데이트
            for (int i = sink; i != source; i = parent[i]) {
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount; // 역방향 간선
            }

            totalFlow += amount;
        }

        return totalFlow;
    }

    static int charToInt(char c) {
        if (c >= 'A' && c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }
}