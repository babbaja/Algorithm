import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] cubes;
    static int[] used;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int width = sc.nextInt();
        int height = sc.nextInt();
        int n = sc.nextInt();

        cubes = new int[n];
        used = new int[n];

        for (int i = 0; i < n; i++) {
            int cubeLength = sc.nextInt();
            int cubeCount = sc.nextInt();
            cubes[i] = cubeCount;
        }

        Arrays.sort(cubes);

        long result = fillBox(length, width, height, 0);

        if (result < 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

        sc.close();
    }

    static long fillBox(int length, int width, int height, int index) {
        if (index >= cubes.length) {
            return 0;
        }

        if (length == 0 || width == 0 || height == 0) {
            return 0;
        }

        int cubeLength = 1 << (cubes.length - index - 1);
        int maxCount = Math.min(cubes[index], Math.min(length / cubeLength, Math.min(width / cubeLength, height / cubeLength)));

        long result = 0;

        for (int count = maxCount; count >= 0; count--) {
            if (used[index] + count <= cubes[index]) {
                used[index] += count;

                long remaining = fillBox(length - count * cubeLength, width, height, index + 1);
                if (remaining < 0) {
                    used[index] -= count;
                } else {
                    result += count + remaining;
                    break;
                }
            }
        }

        if (used[index] > 0) {
            return result;
        } else {
            return -1;
        }
    }
}
