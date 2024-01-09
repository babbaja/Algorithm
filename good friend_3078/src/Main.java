import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int num = sc.nextInt();
        int gap = sc.nextInt();

        long cnt = 0;
        Queue<Integer>[] que = new LinkedList[21];
        for (int i = 0; i < 21; ++i) {
            que[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < num; ++i) {
            String str = sc.next();
            int leng = str.length();
            if (que[leng].isEmpty()) {
                que[leng].add(i);
                continue;
            }
            while (!que[leng].isEmpty()) {
                if (i - que[leng].peek() <= gap) {
                    cnt += que[leng].size();
                    break;
                }
                que[leng].poll();
            }
            que[leng].add(i);
        }

        System.out.println(cnt);
        sc.close();
    }
}