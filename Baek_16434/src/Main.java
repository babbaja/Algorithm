import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int atk;
    static int[][] stage;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        stage = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stage[i][0] = Integer.parseInt(st.nextToken());
            stage[i][1] = Integer.parseInt(st.nextToken());
            stage[i][2] = Integer.parseInt(st.nextToken());
        }

        long low = 1;
        long hi = Long.MAX_VALUE - 1;
        long min = 0;

        while (low <= hi) {
            long mid = low + (hi - low) / 2;

            long curH = mid;
            long curA = atk; 
            boolean clear = true;

            for (int[] s : stage) {
                if (s[0] == 1) { 
                    long kill = s[2] / curA;
                    if (s[2] % curA != 0) kill++;
                    
                    curH -= s[1] * (kill - 1);
                    
                    if (curH <= 0) {
                        clear = false;
                        break;
                    }
                } else if (s[0] == 2) { 
                    curA += s[1];
                    curH += s[2];
                    if (curH > mid) curH = mid; 
                }
            }

            if (clear) {
                hi = mid - 1;
                min = mid; 
            } 
            else {
                low = mid + 1;
            }
        }

        System.out.println(min);
    }
}