import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long Hatk = sc.nextLong();
        long curHP = 0, maxHP = 0;

        for (int i = 0; i < N; ++i) {
            int type = sc.nextInt();
            int attack = sc.nextInt();
            int hp = sc.nextInt();

            if (type == 1) {
                curHP += attack * ( (hp / Hatk) - (hp % Hatk != 0 ? 0 : 1) );
                maxHP = Math.max(maxHP, curHP);
            }
            else {
                Hatk += attack;
                curHP = Math.max(curHP - hp, 0);
            }
        }
        maxHP++;
        System.out.println(maxHP);



    }
}