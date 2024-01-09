import java.util.Scanner;

public class Main {
    static long[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        arr = new long[num];

        for (int i = 0; i < num; ++i) arr[i]= sc.nextLong();
        System.out.println(subarray(0, num));
        sc.close();
    }

    static long subarray(int s, int e) {
        //if (s == e) return arr[s] * arr[s];
        if (s + 1 == e) return arr[s] * arr[s];
        int mid = (s + e) / 2;
        long result = Math.max(subarray(s, mid), subarray(mid, e));

        int r = mid, l = mid;
        long sum = arr[mid], min = arr[mid];
        while (r - l + 1 < e - s) {
            // r을 늘려서 탐색 -> sum += arr[r], min = Math.min(min, arr[r]), l을 늘려서 탐색 -> sum += arr[l], min = Math.min(min, arr[l]
            long p = l > s ? Math.min(arr[l - 1], min) : -1; // 왼쪽으로 확장했을 때
            long q = r < e - 1 ? Math.min(arr[r + 1], min) : -1; // 오른쪽으로 확장했을 때

            if (p > q) {
                min = p;
                sum += arr[l - 1];
                l--;
            }
            else {
                min = q;
                sum += arr[r + 1];
                r++;
            }
            result = Math.max(result, sum * min);
        }
        return result;
    }
}