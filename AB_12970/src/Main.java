import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] count = countAB(N, K);

        if (count[0] == 0 && count[1] == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(AB(count, N, K));
    }

    public static int[] countAB(int N, int K) {
        int a = 0;
        int b = 0;
        for (int i = 1; i <= N / 2; ++i) {
            if (i * (N - i) >= K) {
                a = i;
                b = N - i;
                break;
            }
        }
        return new int[]{a, b};
    }

    public static String AB(int[] count, int N, int K) {
        char[] arr = new char[N];
        Arrays.fill(arr, 'B');


        if (count[0] * count[1] == K) {
            for (int i = 0; i < count[0]; ++i) {
                arr[i] = 'A';
            }
        }
        else {
            for (int i = 0; i < count[0] - 1; ++i) {
                arr[i] = 'A';
            }
            int cnt = K - (N - count[0]) * (count[0] - 1);
            int index = N - 1 - cnt;
            arr[index] = 'A';
        }

        return String.valueOf(arr);
    }
}