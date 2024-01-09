import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] time = new int[num][2];

        for (int i = 0; i < num; ++i) {
            time[i][0] = sc.nextInt(); // start
            time[i][1] = sc.nextInt(); // end
        }

        //오름차순으로 정렬, 시작 시간이 같은 경우, 종료 시간에 따라 오름차순으로 정렬, 람다 표현식으로
        Arrays.sort(time, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(time[0][1]);
        for (int i = 1; i < num; ++i) {
            if (pq.peek() <= time[i][0]) {
                pq.poll();
            }
            pq.offer(time[i][1]);
        }
        System.out.println(pq.size());
        sc.close();
    }
}