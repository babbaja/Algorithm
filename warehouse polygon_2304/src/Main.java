import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] arr = new int[num][2];

        for (int i = 0; i < num; ++i) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] o) -> o[0]));

        int max = 0, index = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (max < arr[i][1]) {
                max = arr[i][1];
                index = i;
            }
        }

        Stack<Integer> st = new Stack<>();
        st.push(arr[0][1]);

        int area = 0;
        int tmp = 0;
        for (int i = 1; i < index; ++i) {
            if (st.peek() < arr[i][1]) {
                area += (arr[i][0] - arr[tmp][0]) * st.pop();
                st.push(arr[i][1]);
                tmp = i;
            }
        }
        area += (arr[index][0] - arr[tmp][0]) * st.pop();

        tmp = num - 1;
        st.push(arr[num - 1][1]);
        for (int i = num - 1; i > index; --i) {
            if (st.peek() < arr[i][1]) {
                area += (arr[tmp][0] - arr[i][0]) * st.pop();
                st.push(arr[i][1]);
                tmp = i;
            }
        }
        area += (arr[tmp][0] - arr[index][0]) * st.pop();
        area += arr[index][1];
        System.out.println(area);

        sc.close();
    }
}