import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static boolean[] ans;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        ans = new boolean[sum + 1];
        ans[sum] = true;
        visited = new boolean[N + 1][sum + 1];

        partialRow(0, 0);

        int index = 0;
        for (int i = 1; i < sum + 1; ++i) {
            if (!ans[i]) {
                index = i;
                break;
            }
        }

        System.out.println(index == 0 ? sum + 1 : index);
    }

    public static void partialRow(int index, int value) {
        ans[value] = true;
        visited[index][value] = true;
        for (int i = index; i < arr.length; ++i) {
            if (!visited[ index + 1][value + arr[index]]) partialRow(i + 1, value + arr[index]);
            if (!visited[index + 1][value]) partialRow(i + 1, value);
        }
    }
}