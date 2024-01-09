import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] time = new int[num][2];
        int cnt = 0;

        for (int i = 0; i < num; ++i) {
            time[i][0] = sc.nextInt();
            time[i][1] = sc.nextInt();
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[1] == i2[1]) {
                    return i1[0] - i2[0];
                }
                return i1[1] - i2[1];
            }
        });

        int min = 0;
        for (int i = 0; i < num; ++i) {
            if (min > time[i][0]) {
                min = time[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
        sc.close();
    }
}