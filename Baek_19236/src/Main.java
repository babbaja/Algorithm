import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;
        int num;
        int dir;
        
        public Node(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
        
    }
    static int[] dx = new int[] {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[] {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      StringTokenizer st = new StringTokenizer(br.readLine());

        Node[][] map = new Node[4][4];
        Node shark;
        int[][] fishes = new int[17][2];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                map[i][j] = new Node(i, j, n, d);
                fishes[n][0] = i;
                fishes[n][1] = j;
            }
        }
        int nn = map[0][0].num;
        shark = new Node(0, 0, nn, map[0][0].dir);
        map[0][0].num = 0;
        map[0][0].x = -1;
        map[0][0].y = -1;
        fishes[nn][0] = -1;
        fishes[nn][1] = -1;
        
        start(map, shark, fishes);
        System.out.println(max);
    }
    
    static void start(Node[][] map, Node shark, int[][] fishes) {
        for (int i = 1; i <= 16; i++) {
            if (fishes[i][0] == -1 && fishes[i][1] == -1) continue;

            int dir = Direction(fishes[i][0], fishes[i][1], map[fishes[i][0]][fishes[i][1]].dir, shark);
            int x = fishes[i][0];
            int y = fishes[i][1];
            map[x][y].dir = dir; //
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int nn = map[nx][ny].num;
            
            Node tmp = new Node(map[nx][ny].x, map[nx][ny].y, map[nx][ny].num, map[nx][ny].dir);
            map[nx][ny] = map[x][y];
            map[x][y] = tmp;
            
            fishes[nn][0] = x;
            fishes[nn][1] = y;
            fishes[i][0] = nx;
            fishes[i][1] = ny;
        }
        
        for (int t = 1; t <= 3; t++) {
            int nx = shark.x + dx[shark.dir] * t;
            int ny = shark.y + dy[shark.dir] * t;
            
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
            if (map[nx][ny].num == 0) continue; //
            Node[][] nmap = new Node[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    nmap[i][j] = new Node(map[i][j].x, map[i][j].y, map[i][j].num, map[i][j].dir);
                }
            }
            Node nShark = new Node(nx, ny, shark.num, shark.dir);
            int[][] nfishes = new int[17][2];
            for (int i = 1; i < 17; i++) {
                nfishes[i][0] = fishes[i][0];
                nfishes[i][1] = fishes[i][1];
            }
            
            int nn = nmap[nx][ny].num;
            nShark.num += nn;
            nShark.dir = map[nx][ny].dir;
            nfishes[nn][0] = -1;
            nfishes[nn][1] = -1;
            nmap[nx][ny].x = -1;
            nmap[nx][ny].y = -1;
            nmap[nx][ny].num = 0;
            
            start(nmap, nShark, nfishes);
            
        }
        max = Math.max(max, shark.num);
    }
    
    static int Direction(int x, int y, int d, Node shark) {
        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark.x && ny == shark.y)) {
                d = d % 8 + 1;
                continue;
            }
            break;
        }
        return d;
    }
}
