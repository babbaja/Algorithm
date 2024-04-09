import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        if (N == 1) {
            System.out.println(arr[0]);
            return;
        }
        Arrays.sort(arr, Comparator.reverseOrder());

        System.out.println(groupingNumber(arr));
    }

    public static int groupingNumber(Integer[] arr) {
        int sum = 0, i = 0;
        while (i < arr.length - 1) {
            if (arr[i] > 1 && arr[i + 1] > 1) {
                sum += arr[i] * arr[i + 1];
                i += 2;
            }
            else {
                break;
            }
        }
        for (; i < arr.length; ++i) {
            if (arr[i] > 0) {
                sum += arr[i];
            }
            else {
                break;
            }
        }

        i = arr.length - 1;
        while (i > 0 && arr[i] <= 0 && arr[i - 1] <= 0) {
            sum += arr[i] * arr[i - 1];
            i -= 2;
        }
        for (; i >= 0; --i) {
            if (arr[i] < 0) {
                sum += arr[i];
            }
            else {
                break;
            }
        }
        return sum;
    }
}
