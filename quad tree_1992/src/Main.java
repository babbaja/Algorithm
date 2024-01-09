import java.util.Scanner;

public class Main {
    static int[][] arr;
    static String str = "";
    static int[] paper = new int[3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        arr = new int[num][num];

        for (int i = 0; i < num; ++i) {
            String tmp = sc.next();
            for (int j = 0; j < num; ++j) {
                //char[][] tmp_char =
                arr[i][j] = tmp.charAt(j) - 48;
            }
        }
        quad_tree(0, 0, num);
        System.out.println(str);
        sc.close();
    }

    static void quad_tree (int row, int col, int num) {
        boolean check = arr_check(row, col, num);
        if (check) {
            str +=  Integer.toString(arr[row][col]);
            return;
        }
        else str += "(";

        int newnum = num / 2;

        quad_tree(row, col, newnum);
        quad_tree(row, col + newnum, newnum);
        quad_tree(row + newnum, col, newnum);
        quad_tree(row + newnum, col + newnum, newnum);
        if (!check) str += ")";
    }

    static boolean arr_check(int row, int col, int num) {
        for (int i = row; i < row + num; ++i)
            for (int j = col; j < col + num; ++j)
                if (arr[row][col] != arr[i][j]) return false;

        return true;
    }
}