package runins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Artscore {
	int score;
	int degree;
	
	public Artscore(int score, int degree) {
		this.score = score;
		this.degree = degree;
	}
}

public class Main {
	static int M;
	static int K;
	static int[][] map = new int[5][5];
	static int[] ruins;
	static int ruinIdx = 0;
	static boolean[][] visited = new boolean[5][5];
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static Artscore[] score;
	static List<int[]> group;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ruins = new int[M];
		
		for (int i = 0; i < 5; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; ++j) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) ruins[i] = Integer.parseInt(st.nextToken());
		
		
		for (int i = 0; i < 5; ++i) Arrays.fill(visited[i], false);
		
		for (int t = 0; t < K; ++t) { 
			score = new Artscore[9];
			maxScore();
			int result = 0;
			while (true) {
				int tmp = explo();
				if (tmp == 0) break;
				result += tmp;
			}
			if (result == 0) break;
			System.out.print(result + " ");
			

//			System.out.println();
//			for (int i = 0; i < 5; ++i) {
//				for (int j = 0; j < 5; ++j) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("다음");
		}
	}
	
	static int explo() {
		//if (ruinIdx >= K) return 0; 
		int result = 0;
//		System.out.println("----전---");
//		for (int i = 0; i < 5; ++i) {
//			for (int j = 0; j < 5; ++j) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		for (int k = 0; k < 5; ++k) Arrays.fill(visited[k], false);
		findAndRemove();
		
//		System.out.println("-----후--");
//		for (int i = 0; i < 5; ++i) {
//			for (int j = 0; j < 5; ++j) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for (int i = 0; i < 5; ++i) {
			for (int j = 4; j >= 0; --j) {
				if (map[j][i] == 0) {
					result += 1;
					map[j][i] = ruins[ruinIdx];
					ruinIdx += 1;
				}
			}
		}
		return result;
	}
	

	static void findAndRemove() {
	    visited = new boolean[5][5];
	    for (int i = 0; i < 5; ++i) {
	        for (int j = 0; j < 5; ++j) {
	            if (!visited[i][j] && map[i][j] != 0) {
	                group = new ArrayList<>();
	                ruinGain(i, j, map[i][j]);

	                if (group.size() >= 3) {
	                    for (int[] pos : group) {
	                        map[pos[0]][pos[1]] = 0;
	                    }
	                }
	            }
	        }
	    }
	}

	static void ruinGain(int x, int y, int value) {
		   visited[x][y] = true;
		    group.add(new int[]{x, y});

		    for (int i = 0; i < 4; ++i) {
		        int nx = x + dx[i];
		        int ny = y + dy[i];

		        if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
		        if (!visited[nx][ny] && map[nx][ny] == value) {
		        	ruinGain(nx, ny, value);
		        }
		    }
		}

	static int dfs(int[][] tmp, int x, int y, int cnt) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
			
			if (!visited[nx][ny] && tmp[x][y] == tmp[nx][ny]) {
				cnt += dfs(tmp, nx, ny, 1);
			}
		}
		
		return cnt;
	}
	
	static void rotateMap(int r, int c, int size) {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                temp[j][size - 1 - i] = map[r + i][c + j];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            	map[r + i][c + j] = temp[i][j];
	}
	
	static int rotate(int[][] tmp, int r, int c, int size, int num) {
        int[][] temp = new int[size][size];

        for (int x = 0; x < num; ++x) {
	        for (int i = 0; i < size; i++)
	            for (int j = 0; j < size; j++)
	            	temp[j][size - 1 - i] = tmp[r + i][c + j];
	                
	        for (int i = 0; i < size; i++)
	            for (int j = 0; j < size; j++)
	            	tmp[r + i][c + j] = temp[i][j];
	        

	        for (int i = 0; i < 5; i++)
	            for (int j = 0; j < 5; j++) {
	            	if (tmp[i][j] == 0) tmp[i][j] = tmp[i][j];
	            }
	        
	        
        }
//        for (int i = 0; i < 5; ++i) {
//			for (int j = 0; j < 5; ++j) {
//				System.out.print(tmp[i][j] + " ");
//			}
//			System.out.println();
//		}

        int cnt = 0;
		for (int k = 0; k < 5; ++k) Arrays.fill(visited[k], false);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				int result = dfs(tmp, i, j, 1);
				if (result >= 3) cnt += result;
			}
		}
//		System.out.println(r + " " + c + " " + num + " " + cnt);
		return cnt;
    }
	
	static int[][] arrayCopy() {
		int[][] tmp = new int[map.length][map[0].length];
		
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}
	
	static void maxScore() {
		int idx = 0;
		for (int i = 1; i <= 3; ++i) {
			for (int j = 1; j <= 3; ++j) {
				//돌리고
				for (int k = 1; k <= 3; ++k) {
					int[][] tmp = arrayCopy();
					int result = rotate(tmp, j - 1, i - 1, 3, k);
					if (score[idx] == null ||
						    score[idx].score < result ||
						    (score[idx].score == result && score[idx].degree > k)) {
						    score[idx] = new Artscore(result, k);
						}

					else if (score[idx].score <= result && score[idx].degree > k) {
						score[idx].score = result;
						score[idx].degree = k;
					}
				}
				++idx;
			}
		}
		
		int maxIdx = 0;
		int max = 0;
		int maxDegree = 0;
		//점수 확인
		for (int i = 0; i < 9; ++i) {
			// 점수 비교 (우선순위 고려)
			if (score[i].score > max ||
			    (score[i].score == max && score[i].degree < maxDegree) ||
			    (score[i].score == max && score[i].degree == maxDegree && i < maxIdx)) {
			    maxIdx = i;
			    max = score[i].score;
			    maxDegree = score[i].degree;
			}

		}
		
		if (max == 0) return;
//		System.out.println("결정 :" + maxIdx + " " + max + " " + maxDegree);
		//비교해서 저장 
		int chkIdx = 0;
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (chkIdx++ == maxIdx) {
					for (int k = 0; k < maxDegree; ++k) {
						rotateMap(j, i, 3);
					}
				}
			}
		}
	}
	
	
}