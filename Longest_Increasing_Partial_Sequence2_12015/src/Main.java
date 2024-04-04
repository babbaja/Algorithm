import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Lis(arr));
    }

    public static int Lis(int[] arr) {
        int[] list = new int[arr.length];
        list[0] = arr[0];
        int size = 1;
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];

            if (list[size - 1] < key) {
                size++;
                list[size - 1] = key;
            }
            else {
                int low = 0;
                int hi = size;
                while (low < hi) {
                    int mid = low + (hi - low) / 2;

                    if (list[mid] < key) {
                        low = mid + 1;
                    }
                    else {
                        hi = mid;
                    }
                }
                list[low] = key;
            }
        }
        return size;
    }
}