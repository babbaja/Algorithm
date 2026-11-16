import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        long low = 0;
        long hi = b + arr[n - 1];
        
        while (low <= hi) {
            long mid = (low + hi) / 2;
            
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (mid <= arr[i]) break;
                
                sum += (mid - arr[i]) * (mid - arr[i]);
                if (sum > b) {
                    break;
                }
            }
            
            if (sum <= b) {
                low = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        
        System.out.println(hi);
    }
}
