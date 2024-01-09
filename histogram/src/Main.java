import java.util.Scanner;

public class Main {
    static int[] H;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        H = new int[num];

        for (int i = 0; i < num; ++i) H[i]= sc.nextInt();
        System.out.println(histogram(0, num));
    }

    static int histogram(int s, int e){
        if(s == e) return 0; // base case: 넓이 0
        if(s + 1 == e) return H[s]; // base case: 넓이 1
        int mid = (s + e)/2;
        int result = Math.max(histogram(s, mid), histogram(mid, e)); // 각 양쪽 구간의 최댓값

        // 양쪽 구간에 걸쳐 있는 답을 O(e-s)에 찾음
        int w = 1, h = H[mid], l = mid, r = mid;
        while(r - l + 1 < e - s){
            // mid 가운데를 기준으로 오른쪽 or 왼쪽으로 계속해서 확장 -> 가장 큰 크기 탐색
            int p = l > s ? Math.min(h, H[l - 1]) : -1; // 왼쪽으로 확장했을 때
            int q = r < e - 1 ? Math.min(h, H[r + 1]) : -1; // 오른쪽확장했을 때
            // 더 높은(같은) 높이를 가진 쪽으로 확장
            if(p >= q){
                h = p;
                l--;
            }
            else{
                h = q;
                r++;
            }
            // 최댓값 갱신
            result = Math.max(result, ++w * h);
        }
        return result;
    }

}