import java.util.Scanner;

public class Main {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        arr = new int[num];

        for (int i = 0; i < num; ++i) arr[i]= sc.nextInt();
        System.out.println(histogram(0, num));
    }

    static int histogram(int s, int e){
        if (s == e) return 0;
        if (s + 1 == e) return arr[s];
        int mid = (s + e) / 2;
        int result = Math.max(histogram(s, mid), histogram(mid, e));

        int l = mid, r = mid, w = 1, h = arr[mid];
        while (r - l + 1 < e - s) {
            int p = l > s ? Math.min(h, arr[l - 1]) : -1;
            int q = r < e - 1 ? Math.min(h, arr[r + 1]) : -1;

            if (p >= q) {
                --l;
                h = p;
            }
            else {
                ++r;
                h = q;
            }
            result = Math.max(result, ++w * h);
        }

        return result;
    }

}