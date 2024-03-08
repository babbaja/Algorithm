import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr;
    public static int[][] group;
    public static ArrayList<Integer> groupNum;
    public static int[] dx = new int[] {-1, 0, 1, 0};
    public static int[] dy = new int[] {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        group = new int[N][M];
        groupNum = new ArrayList<>();
        groupNum.add(0);

        for (int i = 0; i < N; ++i) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < M; ++j) {
                arr[i][j] = list[j] - '0';
            }
        }

        int cnt = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (arr[i][j] == 0 && group[i][j] == 0) {
                    groupNum.add(bfs(i, j, cnt));
                    ++cnt;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(group[i][j] == 0) {
                    sb.append(count(i, j) + "");
                    continue;
                }
                sb.append(0+"");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static int count(int x, int y) {
        int cnt = 1;
        if (arr[x][y] == 0) return 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length) continue;

            set.add(group[nx][ny]);
        }
        for(int num : set) {
            cnt += groupNum.get(num);
        }
        return cnt % 10;
    }

    public static int bfs(int x, int y, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        group[x][y] = cnt;
        int groupnum = 1;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length) continue;

                if (group[nx][ny] == 0 && arr[nx][ny] == 0) {
                    group[nx][ny] = cnt;
                    queue.offer(new int[]{nx,ny});
                    groupnum++;
                }
            }
        }
        return groupnum;
    }
}