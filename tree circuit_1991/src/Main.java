import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[][] tree;
    public static StringBuilder pre = new StringBuilder();
    public static StringBuilder in = new StringBuilder();
    public static StringBuilder post = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new int[N][2];

        for (int i = 0; i < N; ++i) {
            char[] list = br.readLine().toCharArray();
            int k = 0;
            for (int j = 1; j < list.length; ++j) {
                if (list[j] != ' ' && list[j] != '.') {
                    tree[list[0] - 'A'][k] = list[j] - 'A';
                    ++k;
                }
                else if (list[j] == '.') ++k;
            }
        }

        preorder(0);
        System.out.println(pre);
        inorder(0);
        System.out.println(in);
        postorder(0);
        System.out.println(post);

    }

    public static void preorder(int index) {
        pre.append((char)(index + 'A'));
        if (tree[index][0] != 0) {
            preorder(tree[index][0]);
        }
        if (tree[index][1] != 0) {
            preorder(tree[index][1]);
        }
    }

    public static void inorder(int index) {
        if (tree[index][0] != 0) {
            inorder(tree[index][0]);
        }
        in.append((char)(index + 'A'));
        if (tree[index][1] != 0) {
            inorder(tree[index][1]);
        }
    }

    public static void postorder(int index) {
        if (tree[index][0] != 0) {
            postorder(tree[index][0]);
        }
        if (tree[index][1] != 0) {
            postorder(tree[index][1]);
        }
        post.append((char)(index + 'A'));
    }
}