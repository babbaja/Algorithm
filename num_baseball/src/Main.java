import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static boolean[] check = new boolean[1000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 123; i < 1000; ++i) {
            String str = Integer.toString(i);
            char[] str1 = str.toCharArray();
            if (str.charAt(0) == '0' || str.charAt(1) == '0' || str.charAt(2) == '0') continue;
            if (str.charAt(0) == str.charAt(1) || str.charAt(1) == str.charAt(2) || str.charAt(0) == str.charAt(2)) continue;
            check[i] = true;
        }

        for (int t = 0; t < N; ++t) {
            st = new StringTokenizer(br.readLine());
            int req = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            for (int i = 123; i < 1000; ++i) {
                if (check[i]) {
                    int strike_check = 0;
                    int ball_check = 0;
                    for (int j = 0; j < 3; ++j) {
                        char req_split = Integer.toString(req).charAt(j);
                        for (int k = 0; k < 3; ++k) {
                            char ans_split = Integer.toString(i).charAt(k);

                            if(req_split == ans_split && j == k) strike_check++;
                            else if(req_split == ans_split && j != k) ball_check++;
                        }
                    }
                    if (strike_check == strike && ball_check == ball) check[i] = true;
                    else check[i] = false;
                }
            }
        }
        int cnt = 0;
        for (int i = 123; i < 1000; ++i) {
            if (check[i]) ++cnt;
        }
        System.out.println(cnt);
    }
}