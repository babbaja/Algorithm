import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int girl = Integer.parseInt(st.nextToken());
        int boy = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int team = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= K; ++i) {
            int girlIntern = girl - i;
            int boyIntern = boy - (K - i);
            if (girlIntern <= 0 || boyIntern <= 0) continue;

            team = Math.min(girlIntern / 2, boyIntern);
            if (team > max) {
                max = team;
            }
        }

        System.out.println(max);
    }
}