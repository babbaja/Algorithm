import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static String[] word;
    public static int[] alpha = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        word = new String[T];

        for (int t = 0; t < T; ++t) {
            word[t] = br.readLine();
        }

        System.out.println(calcul());
    }

    public static int calcul() {
        int alphanum = 0;
        int result = 0;
        for (int i = 0; i < word.length; ++i) {
            char[] list = word[i].toCharArray();
            int digit = list.length - 1;
            for (int j = 0; j < word[i].length(); ++j) {
                if (alpha[list[j] - 'A']== 0) ++alphanum;
                alpha[list[j] - 'A'] += (int)Math.pow(10, digit--);
            }
        }

        int num = 9;
        for (int k = 0; k < alphanum; ++k) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int i = 0; i < 26; ++i) {
                if (alpha[i] != 0 && max < alpha[i]) {
                    max = alpha[i];
                    index = i;
                }
            }
            result += max * num--;
            alpha[index] = 0;
        }
        return result;
    }
}