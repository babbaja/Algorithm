import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str = br.readLine();
        String key = br.readLine();
        
        int[] alpha = new int[26];
        alpha[9] = 1;
        char[] cipher = new char[25];
        
        int idx = 0;
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : key.toCharArray()) {
            if (set.add(c)) {
                alpha[(int)(c - 'A')] = 1;
                map.put(c, idx);
                cipher[idx++] = c;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (alpha[i] == 0) {
                map.put((char)(i + 'A'), idx);
                cipher[idx++] = (char)(i + 'A');
            }
        }
        
    
        int i = 0;
        char[] list = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (i < list.length) {
            char c1 = list[i];
            char c2;
            if (i + 1 == list.length) c2 = 'X';
            else c2 = list[i + 1];
            
            if (c1 == c2 && i + 1 < list.length) {
                if (c1 == 'X') c2 = 'Q';
                else c2 = 'X';
                i += 1;
            }
            else i += 2;
            
            int n1 = map.get(c1);
            int n2 = map.get(c2);
            
            int x1 = n1 / 5;
            int y1 = n1 % 5;
            int x2 = n2 / 5;
            int y2 = n2 % 5;
            
            
            if (x1 == x2) {
                y1 = (y1 + 1) % 5;
                y2 = (y2 + 1) % 5;
            }
            else if (y1 == y2) {
                x1 = (x1 + 1) % 5;
                x2 = (x2 + 1) % 5;
            }
            else {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            int t1 = x1 * 5 + y1;
            int t2 = x2 * 5 + y2;
            
            sb.append(cipher[t1]).append(cipher[t2]);
        }
        
        System.out.println(sb);
    }
}
