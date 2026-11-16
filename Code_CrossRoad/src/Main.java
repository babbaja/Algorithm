import java.io.*;
import java.util.*;

public class Main {

    static class Car {
        int num;
        int time;
        char road;

        public Car(int num, int time, char road) {
            this.num = num;
            this.time = time;
            this.road = road;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Queue<Car> waiting = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            char road = st.nextToken().charAt(0);

            waiting.offer(new Car(i, time, road));
        }

        int[] pass = new int[n];
        Arrays.fill(pass, -1);

        Queue<Car> aq = new ArrayDeque<>();
        Queue<Car> bq = new ArrayDeque<>();
        Queue<Car> cq = new ArrayDeque<>();
        Queue<Car> dq = new ArrayDeque<>();

        int cur = waiting.peek().time;

        while (!waiting.isEmpty()
                || !aq.isEmpty()
                || !bq.isEmpty()
                || !cq.isEmpty()
                || !dq.isEmpty()) {

            if (aq.isEmpty() && bq.isEmpty() && cq.isEmpty() && dq.isEmpty()) {
                cur = waiting.peek().time;
            }

            while (!waiting.isEmpty() && waiting.peek().time <= cur) {
                Car car = waiting.poll();

                if (car.road == 'A') aq.offer(car);
                if (car.road == 'B') bq.offer(car);
                if (car.road == 'C') cq.offer(car);
                if (car.road == 'D') dq.offer(car);
            }

            boolean hasA = !aq.isEmpty();
            boolean hasB = !bq.isEmpty();
            boolean hasC = !cq.isEmpty();
            boolean hasD = !dq.isEmpty();

            if (hasA && hasB && hasC && hasD) {
                break;
            }
            
            if (hasA && !hasD) {
                Car car = aq.poll();
                pass[car.num] = cur;
            }

            if (hasB && !hasA) {
                Car car = bq.poll();
                pass[car.num] = cur;
            }

            if (hasC && !hasB) {
                Car car = cq.poll();
                pass[car.num] = cur;
            }

            if (hasD && !hasC) {
                Car car = dq.poll();
                pass[car.num] = cur;
            }

            cur++;
        }

        StringBuilder sb = new StringBuilder();

        for (int time : pass) {
            sb.append(time).append('\n');
        }

        System.out.print(sb);
    }
}