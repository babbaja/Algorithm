import java.util.Scanner;

public class Main {
   static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        int size = (int)Math.pow(2, num);

        Z(r, c, size);
        System.out.println(cnt);
        sc.close();
    }

    static void Z (int r, int c, int size) {
        if (size == 1) return;

        if (r < size / 2 && c < size / 2) Z(r, c , size / 2);
        else if (r < size / 2 && c >= size / 2) {
            cnt += size * size / 4;
            Z(r, c - size / 2, size / 2);
        }
        else if (r >= size / 2 && c < size / 2) {
            cnt += (size * size / 4) * 2;
            Z(r - size / 2, c, size / 2);
        }
        else {
            cnt += (size * size / 4) * 3;
            Z(r - size / 2, c - size / 2, size / 2);
        }
    }
}