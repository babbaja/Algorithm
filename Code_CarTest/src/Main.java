import java.io.*;
import java.util.*;

public class Main {
	static int n, q;
	static int[] arr;
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			map.put(arr[i], i);
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			
			int prev = 0;
			int next = 0;
			
			if (map.containsKey(target)) {
				prev = map.get(target);
				next = n - prev - 1;
//				System.out.println(prev + " " + target + " " + next);
				System.out.println(prev * next);
			}
			else System.out.println(0);
		}
	}
}
