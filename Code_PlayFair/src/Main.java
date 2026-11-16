import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = br.readLine();
		String key = br.readLine();
		
		int[] alpha = new int[26];
		char[] cipher = new char[25];
		
		int idx = 0;
		Set<Character> set = new HashSet<>();
		for (char c : key.toCharArray()) {
			if (set.add(c)) {
				alpha[idx] = 1;
				cipher[idx++] = c;
			}
		}
		
		for (int i = 0; i < 26; i++) {
			if (alpha[i] == 0) {
				cipher[idx++] = (char)(i + 'a');
			}
		}
		
		for (int i = 0; i < 25; i++) {
			System.out.print(cipher[i] + " ");
		}
	}
}
