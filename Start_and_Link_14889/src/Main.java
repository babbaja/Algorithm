import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        team(1, 0);
        System.out.println(min);

    }

    public static void team(int index, int num) {
        if (num == arr.length / 2) {
            calcul();
        }

        for (int i = index; i < arr.length; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                team(i + 1, num + 1);
                visited[i] = false;
            }
        }
    }


    public static void calcul() {
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> link = new ArrayList<>();
        for (int i = 1; i < arr.length; ++i) {
            if (!visited[i]) link.add(i);
            else start.add(i);
        }

        int team_start = 0;
        int team_link = 0;
        for (int i = 0; i < start.size(); ++i) {
            for (int j = i + 1; j < start.size(); ++j) {
                team_start += arr[start.get(i)][start.get(j)] + arr[start.get(j)][start.get(i)];
                team_link +=  arr[link.get(i)][link.get(j)] + arr[link.get(j)][link.get(i)];
            }
        }
        if (min > Math.abs(team_link - team_start)) min = Math.abs(team_link - team_start);
    }
}