import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] max = new int[3];
		int[] min = new int[3];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n0 = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			if (i == 0) {
				max[0] = min[0] = n0;
				max[1] = min[1] = n1;
				max[2] = min[2] = n2;
			}
			else {
				int maxp0 = max[0], maxp1 = max[1], maxp2 = max[2];
				int minp0 = min[0], minp1 = min[1], minp2 = min[2];
				
				max[0] = Math.max(maxp0, maxp1) + n0;
				max[1] = Math.max(maxp0, Math.max(maxp1, maxp2)) + n1;
				max[2] = Math.max(maxp1, maxp2) + n2;
				
				min[0] = Math.min(minp0, minp1) + n0;
				min[1] = Math.min(minp0, Math.min(minp1, minp2)) + n1;
				min[2] = Math.min(minp1, minp2) + n2;
			}
		}
		
		int maxr = Math.max(max[0], Math.max(max[1], max[2]));
		int minr = Math.min(min[0], Math.min(min[1], min[2]));
		System.out.println(maxr + " " + minr);
	}
}
