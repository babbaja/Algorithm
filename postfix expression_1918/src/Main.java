import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] list = str.toCharArray();
        Stack<Character> st = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; ++i) {
            if (list[i] >= 'A' && list[i] <= 'Z') sb.append(list[i] + "");
            else {
                if (list[i] == '(') st.push(list[i]);
                else if (list[i] == ')') {
                    while (!st.empty() && st.peek() != '(') sb.append(st.pop());
                    if (!st.empty()) st.pop();
                }
                else {
                    while (!st.empty() && precedence(st.peek()) >= precedence(list[i])) {
                        sb.append(st.pop());
                    }
                    st.push(list[i]);
                }
            }
        }
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
        System.out.println(sb);
        sc.close();
    }
    public static int precedence(char op) {
        if(op == '*' || op == '/') return 2;
        else if(op == '+' || op == '-') return 1;
        else return 0;
    }
}