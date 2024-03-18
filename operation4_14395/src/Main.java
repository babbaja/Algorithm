import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());

        System.out.println(bfs(start, end));
    }

    public static String bfs(long start ,long end) {
        if (start == end) return "0";
        Queue<Long> queue = new LinkedList<>();
        Queue<String> str = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        set.add(start);
        queue.offer(start);
        str.offer("");

        while (!queue.isEmpty()) {
            long tmp = queue.poll();
            String strtmp = str.poll();
            if (tmp == end) {
                return strtmp;
            }

            long first = tmp * tmp;
            if (!set.contains(first)) {
                set.add(first);
                queue.offer(first);
                str.offer(strtmp + "*");
            }

            long second = tmp + tmp;
            if (!set.contains(second)) {
                set.add(second);
                queue.offer(second);
                str.offer(strtmp + "+");
            }

            long third = tmp - tmp;
            if (!set.contains(third)) {
                set.add(third);
                queue.offer(third);
                str.offer(strtmp + "-");
            }

            if (tmp != 0) {
                long forth = tmp / tmp;
                if (!set.contains(forth)) {
                    set.add(forth);
                    queue.offer(forth);
                    str.offer(strtmp + "/");
                }
            }
        }
        return "-1";
    }
}