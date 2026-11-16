package Dice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static boolean[][] visited;
	static int[] curr = new int[] {0 ,0};
	// 0=위, 1=아래, 2=앞, 3=뒤, 4=왼, 5=오
	static int[] dice = new int[]{1, 6, 2, 5, 4, 3};
	static int N;
	static int M;
	static int direction = 0;
	static int totalScore = 0;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		
		for (int i = 0; i < M; ++i) {
			moveDice();	
		}
		
		System.out.println(totalScore);
	}

	static void moveDice() {
		curr[0] += dx[direction];
		curr[1] += dy[direction];
		if (curr[0] >= N || curr[1] >= N || curr[0] < 0 || curr[1] < 0) {
			curr[0] -= dx[direction];
			curr[1] -= dy[direction];
			direction = (direction + 2) % 4;
			curr[0] += dx[direction];
			curr[1] += dy[direction];
		}
		roll(direction);
		for (int i = 0; i < N; ++i) Arrays.fill(visited[i], false); 
		int number = map[curr[0]][curr[1]];
		int count = countSameNumberCells(curr[0], curr[1], number);
		totalScore += number * count;
		if (dice[1] > map[curr[0]][curr[1]]) {
			changeDirection(1);
		}
		else if (dice[1] < map[curr[0]][curr[1]]) {
			changeDirection(-1);
		}
		
	}
	

	static void roll(int dir) {
	    int[] temp = dice.clone();
	    if (dir == 0) { // 오른쪽
	        dice[0] = temp[4];
	        dice[1] = temp[5];
	        dice[4] = temp[1];
	        dice[5] = temp[0];
	    } 
	    else if (dir == 1) { // 아래쪽
	        dice[0] = temp[3];
	        dice[1] = temp[2];
	        dice[2] = temp[0];
	        dice[3] = temp[1];
	    } 
	    else if (dir == 2) { // 왼쪽
	        dice[0] = temp[5];
	        dice[1] = temp[4];
	        dice[4] = temp[0];
	        dice[5] = temp[1];
	    } 
	    else if (dir == 3) { // 위쪽
	        dice[0] = temp[2];
	        dice[1] = temp[3];
	        dice[2] = temp[1];
	        dice[3] = temp[0];
	    }
	}
	
	static void changeDirection(int change) {
		direction += change;
		if (direction > 3) direction = 0;
		else if (direction < 0) direction = 3;
	}
	

	static int countSameNumberCells(int x, int y, int number) {
	    visited[x][y] = true;
	    int count = 1;

	    for (int i = 0; i < 4; i++) {
	        int nx = x + dx[i];
	        int ny = y + dy[i];
	        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
	        if (visited[nx][ny]) continue;
	        if (map[nx][ny] == number) {
	            count += countSameNumberCells(nx, ny, number);
	        }
	    }

	    return count;
	}

}
