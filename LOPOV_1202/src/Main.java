import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Jewel {
        int weigh;
        int price;

        public Jewel(int weigh, int price) {
            this.weigh = weigh;
            this.price = price;
        }
    }
    public static Jewel[] jewels;
    public static int[] bagSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];
        bagSize = new int[K];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int[] tmp = new int[2];
            tmp[0] = Integer.parseInt(st.nextToken());
            tmp[1] = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(tmp[0], tmp[1]);
        }
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                int firstCompare = Integer.compare(o1.weigh, o2.weigh);
                if (firstCompare == 0) {
                    return Integer.compare(o2.price, o1.price);
                }
                return firstCompare;
            }
        });

        for (int i = 0; i < K; ++i) {
            bagSize[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bagSize);

        System.out.println(steel());
    }

    public static Long steel() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        for (int i = 0, j = 0; i < bagSize.length; ++i) {
            while (j < jewels.length && jewels[j].weigh <= bagSize[i]) {
                queue.offer(jewels[j++].price);
            }
            if (!queue.isEmpty()) {
                ans += queue.poll();
            }
        }
        return ans;
    }
}