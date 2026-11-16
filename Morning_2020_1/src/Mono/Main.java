package Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = new int[] {0, 0, 0, -1, 1};
	static int[] dy = new int[] {0, -1, 1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][N];
        int[] status = new int[N+1];
        
        int[][][] direction = new int[N+1][N+1][N+1];
        
		System.out.println("1234");
	}

}
