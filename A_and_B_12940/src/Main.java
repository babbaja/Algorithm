import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        String end = br.readLine();

        System.out.println(AB(start.toCharArray(), end.toCharArray()));
    }

    public static int AB(char[] start, char[] end) {
        int N = end.length;
        char[] list = new char[N];

        int length = N;
        int result = start.length;
        for (int i = N - 1; i >= 0; --i) {
            if (length == result) {
                for (int j = 0; j < result; ++j) {
                    if (start[j] != end[j]) {
                        return 0;
                    }
                }
                break;
            }

            if (end[i] == 'B') {
                for (int j = 0; j < i; ++j) {
                    list[j] = end[i - j - 1];
                }
            }
            else if (end[i] == 'A') {
                for (int j = 0; j < i; ++j) {
                    list[j] = end[j];
                }
            }
            for (int j = 0; j < i; ++j) {
                end[j] = list[j];
            }
            length -= 1;
        }
        return 1;
    }
}