import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = 0;  int P = 0;  int V = 0;
        int i = 1;
        while (true) {
            int day = 0;
            L = sc.nextInt(); P = sc.nextInt(); V = sc.nextInt();
            if (L == 0 && P == 0 && V == 0) break;
            day += (V / P) * L;
            if (V % P >= L) day += L;
            else day += V % P;
            System.out.println("Case " + i + ": " + day);
            ++i;
        }
        sc.close();
    }
}