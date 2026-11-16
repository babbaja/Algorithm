import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] area;
    static HashMap<Integer, Integer> hash;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        area = new int[n][m];
        hash = new HashMap<>();
        hash.put(0, 0);
        for (int i = 0; i < n; i++) {
            char[] list = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = list[j] - '0';
            }
        }
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && area[i][j] == 0) {
                    int cnt = dfs(i, j, num);
                    hash.put(num++, cnt);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    map[i][j] += wall(i, j);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    static int wall(int x, int y) {
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (set.contains(area[nx][ny])) continue;
            
            cnt += hash.get(area[nx][ny]);
            set.add(area[nx][ny]);
        }
        
        return cnt;
    }
    
    static int dfs(int x, int y, int num) {
        int cnt = 1;
        area[x][y] = num;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (map[nx][ny] != 0 || area[nx][ny] != 0) continue;
            
            cnt += dfs(nx, ny, num);
        } 
        return cnt;
    }
}
