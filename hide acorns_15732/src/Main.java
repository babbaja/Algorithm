import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int D = sc.nextInt();

        int[][] rule = new int[K][3];
        for (int i = 0; i < K; ++i)
            for (int j = 0; j < 3; ++j)
                rule[i][j] = sc.nextInt();
        int s = 0, e = N;
        while (s <= e) {
            int mid = (s + e) / 2;
            long cnt = 0;

            for (int i = 0; i < K; ++i) {
                if (mid >= rule[i][1]) cnt += (rule[i][1] - rule[i][0]) / rule[i][2] + 1;
                else if (mid < rule[i][0]) continue;
                else {
                    cnt += (mid - rule[i][0]) == 0 ? 1 : (mid - rule[i][0]) / rule[i][2] + 1;
                }
            }
            if (cnt >= D) e = mid - 1;
            else s = mid + 1;
        }
        System.out.println(s);
       sc.close();
    }
}