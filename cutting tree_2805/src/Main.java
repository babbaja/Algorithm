import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int height = sc.nextInt();
        int[] arr = new int[num];

        for (int i = 0; i < num; ++i) arr[i] = sc.nextInt();
        int MAX = 0;
        for (int i = 0; i < num; i++) {
            if (arr[i] > MAX) {
                MAX = arr[i];
            }
        }

        int s = 0, e = MAX;
        while (s +1 < e) {
            int  mid = (s + e) / 2;
            long sum = 0;
            for (int i = 0; i < num; ++i) {
                if (arr[i] > mid) sum += arr[i] - mid;
            }
            if (sum >= height) s = mid;
            else e = mid;
        }
        System.out.println(s);

        sc.close();
    }

}