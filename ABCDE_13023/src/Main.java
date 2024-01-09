import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[M][2];
        visited = new boolean[N];

        for (int i = 0; i < M; ++i) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; ++i) {
            visited[arr[i][0]] = true;
            visited[arr[i][1]] = true;
            abcde(0, i);
        }
        
    }

    public static int abcde(int num, int index) {
        int a = arr[index][0];
        int b = arr[index][1];
        num += 1;
        if (num == 4) {
            return 1;
        }
        else if (num == 1) {
            for (int i = index + 1; i < arr.length; ++i) {
                if (!visited[arr[i][0]]) {
                    visited[arr[i][0]] = true;
                }
                else if (!visited[arr[i][1]]) {
                    visited[arr[i][1]] = true;
                }
            }
        }
        else {

        }

        return abcde(num, index + 1);
    }
}