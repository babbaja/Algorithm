import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 *  2019136125 조승대
 * 0, 1, 2, 3을 이용하여 구성된 격자 맵이 주어짐
 * 0: 통로
 * 1: 시작위치
 * 2: 보석
 * 3: 벽
 * 목적: 시작 위치에서 보석까지의 최단 경로 찾기
 * 이동은 상하좌우로만 할 수 있음
 * 맵에는 항상 보석이 하나 주어짐
 * 보석을 찾을 수 없으면 최단 경로의 길이는 -1을 출력해야 함
 * 주어진 해결책에서 코드 냄새를 찾아 리펙토링하세요.
 * 요구사항. 주석에 다음을 포함하여 주세요.
 * 1) 리펙토링한 순서
 * 2) 각 코드 냄새를 제거하기 위해 리펙토링한 방법
 * 강의 슬라이드에 제시된 코드 냄새와 정확한 매칭이 되지 않지만
 * 코드를 개선할 수 있는 부분을 발견하여 개선하면 그것도
 * 제시한 순서에 포함해야 함
 *
 * 함수 여러개로 나누기, 코드 중복 없애기
 */
public class Main {
    // BFS를 이용하여 시작 위치부터 보석을 찾는다.
    public static int solve(int[][] map) {
        // 시작 위치 탐색
        int startR = 0;
        int startC = 0;
        FOUND:
        for(int r = 0; r < map.length; ++r)
            for(int c = 0; c < map[0].length; ++c) {
                if(map[r][c] == 1) {
                    startR = r;
                    startC = c;
                    break FOUND;
                }
            }
        // 시작 위치부터 BFS 수행
        int minLength = -1;
        boolean[][] visited = new boolean[map.length][map[0].length];

        /*
        Queue<Integer> rowQueue = new ArrayDeque<>();
        Queue<Integer> colQueue = new ArrayDeque<>();
        Queue<Integer> lengthQueue = new ArrayDeque<>();
        rowQueue.add(startR);
        colQueue.add(startC);
        lengthQueue.add(0);
         visited[startR][startC] = true;
		while(!rowQueue.isEmpty()) {
			int currR = rowQueue.poll();
			int currC = colQueue.poll();
			int length = lengthQueue.poll();
			// 4개 방향으로 탐색 진행

			//함수로 만들기


			if(currR + 1 < map.length && !visited[currR + 1][currC]) {
				if(map[currR + 1][currC] == 2) {
					minLength = length+1;
					break;
				}
				else if(map[currR + 1][currC] == 0) {
					visited[currR + 1][currC] = true;
					rowQueue.add(currR + 1);
					colQueue.add(currC);
					lengthQueue.add(length+1);
				}
			}
			if(currR - 1 >= 0 && !visited[currR - 1][currC]) {
				if(map[currR-1][currC] == 2) {
					minLength = length+1;
					break;
				}
				else if(map[currR - 1][currC] == 0) {
					visited[currR - 1][currC] = true;
					rowQueue.add(currR - 1);
					colQueue.add(currC);
					lengthQueue.add(length + 1);
				}
			}
			if(currC + 1 < map[0].length && !visited[currR][currC + 1]) {
				if(map[currR][currC + 1] == 2) {
					minLength = length + 1;
					break;
				}
				else if(map[currR][currC + 1] == 0) {
					visited[currR][currC + 1] = true;
					rowQueue.add(currR);
					colQueue.add(currC + 1);
					lengthQueue.add(length + 1);
				}
			}
			if(currC - 1 >= 0 && !visited[currR][currC - 1]) {
				if(map[currR][currC - 1] == 2) {
					minLength = length + 1;
					break;
				}
				else if(map[currR][currC - 1] == 0) {
					visited[currR][currC - 1] = true;
					rowQueue.add(currR);
					colQueue.add(currC - 1);
					lengthQueue.add(length + 1);
				}
			}
		}*/
        //return minLength;
        return bfs(map, visited, startR, startC);
    }
    //너무 길었던 solve 함수를 2개의 함수로 나누어 긴 함수를 짧게 만듬
    public static int bfs(int[][] map, boolean[][] visited, int R, int C) {
        int numR = map.length;
        int numC = map[0].length;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minLength = -1;
        Queue<Integer> rowQueue = new ArrayDeque<>();
        Queue<Integer> colQueue = new ArrayDeque<>();
        Queue<Integer> lengthQueue = new ArrayDeque<>();
        rowQueue.add(R);
        colQueue.add(C);
        lengthQueue.add(0);
        visited[R][C] = true;

		while(!rowQueue.isEmpty()) {
            int currR = rowQueue.poll();
            int currC = colQueue.poll();
            int length = lengthQueue.poll();
            // 중복 되어 있는 코드들을 for 문으로 만들어 하나의 반복문으로 묶음
            for (int[] dir : direction) {
                int newR = currR + dir[0];
                int newC = currC + dir[1];
                if (newR >= 0 && newR < numR && newC >= 0 && newC < numC && !visited[newR][newC]) {
                    if (map[newR][newC] == 2) {
                        minLength = length + 1;
                        break;
                    }
                    else if (map[newR][newC] == 0) {
                        visited[newR][newC] = true;
                        rowQueue.add(newR);
                        colQueue.add(newC);
                        lengthQueue.add(length + 1);
                    }
                }

            }
        }
        return minLength;
    }

    public static void main(String[] args) {

        int[][] map = {
                {3, 0, 3, 0, 3, 1, 3},
                {3, 0, 0, 0, 3, 0, 3},
                {3, 0, 3, 0, 0, 0, 3},
                {3, 0, 3, 3, 3, 0, 3},
                {3, 0, 0, 2, 3, 0, 0},
                {3, 3, 3, 3, 3, 3, 3}
        };
        System.out.println(solve(map));
        map = new int[][]{
                {3, 3, 3, 0, 3, 0, 3},
                {3, 0, 0, 0, 3, 0, 3},
                {1, 0, 3, 0, 3, 0, 3},
                {3, 0, 3, 3, 3, 0, 3},
                {3, 0, 3, 2, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 3}
        };
        System.out.println(solve(map));


        map = new int[][]{
                {3, 3, 3, 0, 3, 3, 3},
                {3, 0, 0, 0, 3, 2, 3},
                {1, 0, 3, 0, 0, 0, 3},
                {3, 0, 3, 3, 3, 0, 3}
        };
        System.out.println(solve(map));
    }
}
