import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Integer> energies;
    public static int max= Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        energies = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            energies.add(Integer.parseInt(st.nextToken()));
        }

        dfs(0);

        System.out.println(max);
    }

    private static void dfs(int sum) {
        if(energies.size() <= 2) {
            if(max < sum) {
                max = sum;
            }
            return;
        }

        for(int i = 1; i < energies.size() - 1; ++i) {
            int temp = energies.get(i);
            int num = energies.get(i - 1) * energies.get(i + 1);
            energies.remove(i);
            dfs(sum + num);
            energies.add(i, temp);
        }
    }
}
