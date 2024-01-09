import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static char[][] arr;
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            char list[] = str.toCharArray();
            for (int j = 0; j < N; ++j) {
                arr[i][j] = list[j];
            }
        }

    }
}