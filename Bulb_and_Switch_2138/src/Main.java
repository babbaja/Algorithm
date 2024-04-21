import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arr1 = new int[N];
        int[] ans = new int[N];

        char[] list = br.readLine().toCharArray();
        for (int i = 0; i < N; ++i) {
            arr[i] = list[i] - '0';
            arr1[i] = arr[i];
        }
        list = br.readLine().toCharArray();
        for (int i = 0; i < N; ++i) {
            ans[i] = list[i] - '0';
        }

        arr1[0] = arr1[0] == 1 ? 0 : 1;
        arr1[1] = arr1[1]  == 1 ? 0 : 1;
        int answer = OnOff(arr, ans);
        int answer2 = OnOff(arr1, ans);
        if (answer2 != -1) answer2++;

        if (answer == -1)
            System.out.println(answer2);
        else if (answer2 == -1)
            System.out.println(answer);
        else
            System.out.println(Math.min(answer2, answer));
    }

    public static int OnOff(int[] arr, int[] ans) {
        int[] dx = new int[]{0, 1, 2};
        int tries = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] == ans[i]) continue;

            ++tries;
            for (int j = 0; j < 3; ++j) {
                int num = i + dx[j];
                if (num >= arr.length) continue;
                arr[num] = (arr[num] == 1) ? 0 : 1;
            }
        }
        return arr[arr.length - 1] == ans[arr.length - 1] ? tries : - 1;
    }
}