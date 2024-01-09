import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int[] number = {-1, 0, 1};
    static int[] paper = new int[3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        arr = new int[num][num];

        for (int i = 0; i < num; ++i)
            for (int j = 0; j < num; ++j)
                arr[i][j] = sc.nextInt();

        paper_num(0, 0, num);
        System.out.println(paper[0]);
        System.out.println(paper[1]);
        System.out.println(paper[2]);
    }

    static void paper_num (int row, int col, int num) {
        if (arr_check(row, col, num)) {
           ++paper[arr[row][col] + 1];
           return;
        }
        int newnum = num / 3;

        paper_num(row, col, newnum);
        paper_num(row, col + newnum, newnum);
        paper_num(row, col + 2 * newnum, newnum);

        paper_num(row + newnum, col, newnum);
        paper_num(row + newnum, col + newnum, newnum);
        paper_num(row + newnum, col + 2 *  newnum, newnum);

        paper_num(row + 2 * newnum, col, newnum);
        paper_num(row + 2 * newnum, col + newnum, newnum);
        paper_num(row + 2 * newnum, col + 2 *  newnum, newnum);

    }

    static boolean arr_check(int row, int col, int num) {
        for (int i = row; i < row + num; ++i)
            for (int j = col; j < col + num; ++j)
                if (arr[row][col] != arr[i][j]) return false;

        return true;
    }
}