import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        int s = 0, e = 0;

        for (int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();
            if (s < arr[i]) s = arr[i];
            e += arr[i];
        }

        while (s <= e) {
            int mid = (s + e) / 2;
            int sum = 0, cnt = 0;

            for (int i = 0; i < N; i++) {
                if (sum + arr[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum = sum + arr[i];
            }
                if (sum != 0) cnt++;
                if (cnt > M) s = mid + 1;
                else e = mid - 1;
        }
         System.out.println(s);
        sc.close();
    }

}