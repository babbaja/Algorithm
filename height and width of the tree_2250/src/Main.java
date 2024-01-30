import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[][] tree;
    public static int[] hang;
    public static ArrayList<ArrayList<Integer>> level;
    public static int yeol = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];
        hang = new int[N + 1];
        level = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            tree[index][0] = Integer.parseInt(st.nextToken());
            tree[index][1] = Integer.parseInt(st.nextToken());
        }

        level.add(new ArrayList<Integer>());
        inorder(findRoot(), 1);

        int ans_value = 0;
        int ans_level = 0;
        for (int i = 1; i < level.size(); ++i ){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int n : level.get(i)) {
               if (max < hang[n]) max = hang[n];
               if (min > hang[n]) min = hang[n];
            }

            if (ans_value < max - min + 1) {
                ans_value = max - min + 1;
                ans_level = i;
            }
        }

        System.out.printf("%d %d\n", ans_level, ans_value);
    }


    public static int findRoot() {
        boolean[] isChild = new boolean[tree.length];
        for (int i = 1; i < isChild.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (tree[i][j] != -1) {
                    isChild[tree[i][j]] = true;
                }
            }
        }
        for (int i = 1; i < isChild.length; i++) {
            if (!isChild[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void inorder(int index, int depth) {
        if (level.size() <= depth) {
            level.add(new ArrayList<Integer>());
        }
        level.get(depth).add(index);

        if (tree[index][0] != -1) {
            inorder(tree[index][0], depth + 1);
        }
        hang[index] = yeol++;
        if (tree[index][1] != -1) {
            inorder(tree[index][1], depth + 1);
        }
    }
}