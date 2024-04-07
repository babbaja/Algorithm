import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String[] list = str.split("-");
        System.out.println(calcul(list));
    }

    public static int calcul(String[] list) {
        int[] num = new int[list.length];
        for (int i = 0; i < list.length; ++i) {
            String[] number = list[i].split("\\+");
            int sum = 0;
            for (int j = 0; j < number.length; ++j) {
                sum += Integer.parseInt(number[j]);
            }
            num[i] = sum;
        }

        int min = num[0];
        for (int i = 1; i < num.length; ++i) {
            min -= num[i];
        }
        return min;
    }
}