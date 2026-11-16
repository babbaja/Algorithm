import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        st = new StringTokenizer(br.readLine());
        int rx = Integer.parseInt(st.nextToken());
        int ry = Integer.parseInt(st.nextToken());
        int rd = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = clean(rx, ry, rd);
        System.out.println(ans);
    }
    
    static int clean(int rx, int ry, int rd) {
        int cnt = 0;
        while (true) {
            if (map[rx][ry] == 0) {
                cnt++;
                map[rx][ry] = 2;
            }
            
            boolean dirty = false;
            
            for (int i = 0; i < 4; i++) {
                rd -= 1;
                if (rd < 0) rd = 3;
                int nx = rx + dx[rd];
                int ny = ry + dy[rd];
                
                if (map[nx][ny] == 0) {
                    rx = nx;
                    ry = ny;
                    dirty = true;
                    break;
                }
            }
            
            if (!dirty) {
                int nx = rx - dx[rd];
                int ny = ry - dy[rd];
                if (map[nx][ny] == 1) return cnt;
                rx = nx;
                ry = ny;
            }
        }
    }
}
