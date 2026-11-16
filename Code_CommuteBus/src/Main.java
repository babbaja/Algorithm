import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        for (int i = 0; i < n - 2; i++) {
            int cnt = 0;
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                	cnt++;
                } 
                else {
                    answer += cnt;
                }
            }
        }

        System.out.println(answer);
    }
}