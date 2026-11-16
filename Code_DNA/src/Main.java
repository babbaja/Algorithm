import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static String[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sequence = new String[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = br.readLine();
        }

        int size = 1 << n;

        boolean[] coverable = new boolean[size];

        for (int mask = 1; mask < size; mask++) {
            coverable[mask] = canCover(mask);
        }

        int[] dp = new int[size];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;

        for (int mask = 1; mask < size; mask++) {
            int first = mask & -mask;

            for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
                if ((sub & first) == 0) continue;

                if (coverable[sub]) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ sub] + 1);
                }
            }
        }

        System.out.println(dp[size - 1]);
    }

    static boolean canCover(int mask) {
        for (int pos = 0; pos < m; pos++) {
            char fixed = '.';

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) continue;

                char current = sequence[i].charAt(pos);

                if (current == '.') continue;

                if (fixed == '.') {
                    fixed = current;
                } 
                else if (fixed != current) {
                    return false;
                }
            }
        }

        return true;
    }
}