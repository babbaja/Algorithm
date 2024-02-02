import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int k = 0;
    public static char[] list;
    public static boolean[] visited;
    public static int[] ans;
    public static StringBuilder max = new StringBuilder();
    public static StringBuilder min = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        list = new char[k + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = st.countTokens();
        for (int i = 0; i < num; ++i) {
            list[i] = st.nextToken().charAt(0);
        }
        visited = new boolean[k + 1];
        ans = new int[k + 1];
        findmax();
        System.out.println(max);

        visited = new boolean[k + 1];
        ans = new int[k + 1];
        findmin();
        System.out.println(min);
    }

    public static void findmax() {
        int num = 9;
        for (int i = 0; i < k + 1; ++i) { // num
            for (int j = 0; j < k + 1; ++j) { // sign
                if (list[j] == '>' && !visited[j]) {
                    ans[j] = num - i;
                    visited[j] = true;
                    break;
                }
                else if (list[j] == '<' && !visited[j]) {
                    if (visited[j + 1]) {
                        ans[j] = num - i;
                        visited[j] = true;
                        break;
                    }
                }
                else if (!visited[j]){
                   ans[j] = num - i;
                   visited[j] = true;
                }
            }
        }

        for (int i : ans) {
            max.append(i);
        }
    }

    public static void findmin() {
        int num = 0;
        for (int i = 0; i < k + 1; ++i) { // num
            for (int j = 0; j < k + 1; ++j) { // sign
                if (list[j] == '<' && !visited[j]) {
                    ans[j] = num + i;
                    visited[j] = true;
                    break;
                }
                else if (list[j] == '>' && !visited[j]) {
                    if (visited[j + 1]) {
                        ans[j] = num + i;
                        visited[j] = true;
                        break;
                    }
                }
                else if (!visited[j]){
                    ans[j] = num + i;
                    visited[j] = true;
                }
            }
        }

        for (int i : ans) {
            min.append(i);
        }
    }
}