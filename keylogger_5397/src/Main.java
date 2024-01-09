import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    static LinkedList<Character> linklist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; ++i) {

            linklist = new LinkedList<>();
            ListIterator<Character> list = linklist.listIterator();
            String str = sc.next();
            char[] tmp = str.toCharArray();
            for (int j = 0; j < str.length(); ++j) {
                if (tmp[j] == '<') {
                    if (list.hasPrevious()) list.previous();
                } else if (tmp[j] == '>') {
                    if (list.hasNext()) list.next();
                } else if (tmp[j] == '-') {
                    if (list.hasPrevious()) {
                        list.previous();
                        list.remove();
                    }
                } else list.add(tmp[j]);
            }
            StringBuilder sb = new StringBuilder();
            for (char s : linklist) {
                sb.append(s);
            }
            System.out.println(sb.toString());
        }

        sc.close();
    }
}
