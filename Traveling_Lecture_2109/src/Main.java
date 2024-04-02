import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Lecture {
        int day;
        int price;

        public Lecture(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }

    public static int[] max = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Lecture[] lec = new Lecture[N];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            lec[i] = new Lecture(day, price);
        }
        Arrays.sort(lec, (l1, l2) -> Integer.compare(l2.price, l1.price));

        System.out.println(travle(lec));
    }

    public static int travle(Lecture[] lec) {
        int totalPrice = 0;
        for (int i = 0; i < lec.length; ++i) {
            for (int j = lec[i].day; j > 0; --j) {
                if (max[j] < lec[i].price) {
                    max[j] = lec[i].price;
                    totalPrice += lec[i].price;
                    break;
                }
            }
        }
        return totalPrice;
    }
}