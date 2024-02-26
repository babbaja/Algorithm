import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static boolean[] visited;
    public static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = 0;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            arr = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < k; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            lottonum(0, 0);
            System.out.println();
        }
    }

    public static void lottonum(int index, int depth) {
        if (depth == 6) {
           for (int i : ans) {
               System.out.printf("%d ", i);
           }
            System.out.println();
        }

        for (int i = index; i < arr.length; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                ans.add(arr[i]);
                lottonum(i + 1, depth + 1);
                visited[i] = false;
                ans.remove(ans.size() - 1);
            }
        }

    }
}