import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N;
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; ++i) {
            String s = br.readLine();
            for (int j = 0; j < N; ++j) {
                map[i][j] = s.charAt(j);
            }
        }
        int ans = flip();
        System.out.println(ans);
    }

    public static int flip() {
        int answer = Integer.MAX_VALUE;
        for (int bit = 1; bit < (1 << N); ++bit) {
            int sum = 0;
            for (int j = 0; j < N; ++j) {
                int tail = 0;
                for (int i = 0; i < N; ++i) {
                    char curr = map[i][j];
                    if ((bit & (1 << i)) != 0) {
                        curr = reverse(curr);
                    }
                    if (curr == 'T')
                        tail++;
                }
                sum += Math.min(tail, N - tail);
            }
            answer = Math.min(answer, sum);
        }
        return answer;
    }
    public static char reverse(char curr) {
        return curr == 'T' ? 'H' : 'T';
    }
}
