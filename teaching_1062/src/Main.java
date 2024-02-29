import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static String[] arr;
    public static boolean[] visited = new boolean[26];
    public static int N;
    public static int K;
    public static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (K < 5) {
            System.out.println(0);
            return;
        }
        else if (K == 26) {
            System.out.println(N);
            return;
        }
        arr = new String[N];

        visited[0] = true;
        visited[2] = true;
        visited[8] = true;
        visited[13] = true;
        visited[19] = true;

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            str = str.substring(4, str.length() - 4);
            arr[i] = str;
        }
        words(0, 5);
        System.out.println(ans);
    }

    public static void words(int index, int alphaNum) {
        if (alphaNum == K) {
            int num = 0;
            for (int i = 0; i < N; ++i) {
                char[] list = arr[i].toCharArray();
                boolean check = true;
                for (char c : list) {
                    if (!visited[c - 'a']) {
                        check = false;
                        break;
                    }
                }
                if (check) num++;
            }
            ans = Math.max(ans, num);
            return;
        }

        for (int i = index; i < 26; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                words(i + 1, alphaNum + 1);
                visited[i] = false;
            }
        }
    }
}