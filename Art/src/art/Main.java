package art;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] group;
	static int N;
	static int totalScore = 0;
	static boolean[][] visited;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		group = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		for (int i = 0; i < 4; ++i) {
			visited = new boolean[N][N];
			for (int j = 0; j < N; ++j) Arrays.fill(visited[j], false);
			artScore();
			circul();
		}
		
		System.out.println(totalScore);
	}

	static void area(int x, int y, int num) {
		visited[x][y] = true;
		group[x][y] = num;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			
			if (map[x][y] == map[nx][ny] && !visited[nx][ny]) {
				area(nx, ny, num);
			}
		}
	}
	
	static void artScore() {
		int num = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!visited[i][j]) area(i, j, ++num);
			}
		}
		
		int[] count = new int[num];
		int[] number = new int[num];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				++count[group[i][j] - 1];
				number[group[i][j] - 1] = map[i][j];
			}
		}
		
		int[] score = new int[num * (num - 1) / 2];
		int idx = 0;
		for (int i = 1; i <= num; ++i) {
			for (int j = i + 1; j <= num; ++j) {
				score[idx++] += near(i, j);
			}
		}
		
		idx = 0;
		int art = 0;
//		System.out.println("number");
//		for (int i = 0; i < number.length; ++i) System.out.print(number[i] + ", ");
//		System.out.println();
//		System.out.println("count");
//		for (int i = 0; i < count.length; ++i) System.out.print(count[i] + ", ");
//		System.out.println();
//		System.out.println("score");
//		for (int i = 0; i < score.length; ++i) System.out.print(score[i] + ", ");
//		System.out.println();
		
		for (int i = 1; i <= num; ++i) {
			for (int j = i + 1; j <= num; ++j) {
				art += (count[i - 1] + count[j - 1]) * number[i - 1] * number[j - 1] * score[idx++];
			}
		}
//		System.out.println(art);
		totalScore += art;
		//회전
		
	}
	
	static int near(int a, int b) {
		int cnt = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (group[i][j] == a) {
					cnt += nearChk(i, j, b);
				}
			}
		}
		
		return cnt; 
	}
	
	static int nearChk(int x, int y, int b) {
		int cnt = 0;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			
			if (group[nx][ny] == b) ++cnt;
		}
		
		return cnt;
	}
	
	static void circul() {
		
		for (int i = 0; i < N / 2; ++i) {
			int tmp = map[i][N / 2];
			map[i][N / 2] = map[N / 2][N - 1 - i];
			map[N / 2][N - 1 - i] = map[N - 1 - i][N / 2];
			map[N - 1 - i][N / 2] = map[N / 2][i];
			map[N / 2][i] = tmp;
		}
		rotateQuadrants();
	}
	
	static void rotateQuadrants() {
        int half = N / 2;

        rotateSubGrid(0, 0, half);             
        rotateSubGrid(0, half + 1, half);     
        rotateSubGrid(half + 1, 0, half);      
        rotateSubGrid(half + 1, half + 1, half); 
    }

    static void rotateSubGrid(int r, int c, int size) {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                temp[j][size - 1 - i] = map[r + i][c + j];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            	map[r + i][c + j] = temp[i][j];
    }

}
