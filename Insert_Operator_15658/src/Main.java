import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static int[] operatorNum = new int[4];
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = st.countTokens();
        for (int i = 0; i < num; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) {
            operatorNum[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int value, int index) {
        if (index == arr.length) {
            max = (int)Math.max(max, value);
            min = (int)Math.min(min, value);
            return;
        }

        for (int i = 0; i < 4; ++i) {
            if (operatorNum[i] > 0) {
                operatorNum[i]--;
                if (i == 0) dfs(value + arr[index], index + 1);
                else if (i == 1) dfs(value - arr[index], index + 1);
                else if (i == 2) dfs(value * arr[index], index + 1);
                else dfs(value / arr[index], index + 1);
                operatorNum[i]++;
            }
        }
    }
}