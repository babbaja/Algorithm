import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] arr;
    static boolean[] check;
    static boolean[] finished;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; ++t) {
            int num = sc.nextInt();
            arr = new int[num + 1];
            check = new boolean[num + 1];
            finished = new boolean[num + 1];
            cnt = 0;
            for (int i = 1; i <= num; ++i) arr[i] = sc.nextInt();
            for (int i = 1; i <= num; ++i) dfs(i);


            System.out.println(num - cnt);
        }
        sc.close();
    }

    static void dfs(int index) {
        if (check[index]) return;

        check[index] = true;
        int next = arr[index];
        if (!check[next]) dfs(next);
        else {
            if (!finished[next]) {
                cnt++;
                for (int i = next; i != index; i = arr[i]) cnt++;
            }
        }
        finished[index] = true;
    }
}