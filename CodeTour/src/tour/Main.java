package tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

class Destination implements Comparable<Destination> {
	int to;
	int weight;
	
	public Destination(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Destination o) {
		return this.weight - o.weight;
	}
}

class Tour implements Comparable<Tour>{
	int idx;
	int revenue;
	int dest;
	int cost;
	boolean isSell;
	
	public Tour(int idx, int revenue, int dest, int cost, boolean isSell) {
		this.idx = idx;
		this.revenue = revenue;
		this.dest = dest;
		this.cost = cost;
		this.isSell = isSell;
	}
	
	@Override
	public int compareTo(Tour o) {
		if (this.isSell != o.isSell) {
            return o.isSell ? 1 : -1;
        }
		else if ((this.revenue - this.cost) != (o.revenue - o.cost)) {
            return (o.revenue - o.cost) - (this.revenue - this.cost); // 내림차순 
        } 
		else {
            return this.idx - o.idx; // 오름차순 
        }
	}
 	
}

public class Main {
	final static int INF = 0x7fffffff;
	static PriorityQueue<Tour> tour = new PriorityQueue<>();
	static int[][] land;
	static int depart = 0;
	static int[] D = new int[2000];
	static int N;
	static int M;
	static boolean[] isMade = new boolean[30001];
	static boolean[] isCanceled = new boolean[30001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Q = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < Q; ++t) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if (num == 100) {
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				land = new int[M][M];

				for (int i = 0; i < M; i++) {
		            Arrays.fill(land[i], INF);
		            land[i][i] = 0;
		        }
				for (int i = 0; i < M; ++i) {
					int v1 = Integer.parseInt(st.nextToken());
					int v2 = Integer.parseInt(st.nextToken());
					int weight = Integer.parseInt(st.nextToken());

		            land[v1][v2] = Math.min(land[v1][v2], weight);
		            land[v2][v1] = Math.min(land[v2][v1], weight);
				}

				dijkstra();
			}
			else if (num == 200) {
				int idx = Integer.parseInt(st.nextToken());
				int revenue = Integer.parseInt(st.nextToken());
				int destination = Integer.parseInt(st.nextToken());
				
				boolean check = true;
				if (D[destination] == Integer.MAX_VALUE || D[destination] > revenue) check = false;
				
				Tour tourGoods = new Tour(idx, revenue, destination, D[destination], check);
				tour.offer(tourGoods);
			    isMade[idx] = true;
			    isCanceled[idx] = false;

			}
			else if (num == 300) {
			    int targetIdx = Integer.parseInt(st.nextToken());
			    if (isMade[targetIdx]) {
			        isCanceled[targetIdx] = true;
			    }
			}

			else if (num == 400) {
			    boolean printed = false;

			    while (!tour.isEmpty()) {
			        Tour tmp = tour.peek();
			        if (!tmp.isSell) break;
			        if (isCanceled[tmp.idx]) {
			            tour.poll();
			        } else {
			            System.out.println(tmp.idx);
			            tour.poll();
			            printed = true;
			            break;
			        }
			    }

			    if (!printed) System.out.println(-1);
			}

			else if (num == 500) {
				int change = Integer.parseInt(st.nextToken());
				depart = change;
				changeDepart();
			}	
		}
//		for (Tour tmp : tour) {
//			System.out.println(tmp.idx + " " + tmp.revenue + " " + tmp.dest + " " + tmp.cost + " " + tmp.isSell);
//		}
	}
	
	static void dijkstra() {
	    PriorityQueue<Destination> pq = new PriorityQueue<>();
	    Arrays.fill(D, INF);
	    D[depart] = 0;
	    pq.offer(new Destination(depart, 0));

	    while (!pq.isEmpty()) {
	    	Destination curr = pq.poll();
	        int now = curr.to;
	        int dist = curr.weight;

	        if (D[now] < dist) continue; // 이미 더 짧은 거리로 처리된 경우 skip

	        for (int next = 0; next < N; next++) {
	            if (land[now][next] != INF) { // 연결된 정점만
	                int cost = D[now] + land[now][next];
	                if (D[next] > cost) {
	                    D[next] = cost;
	                    pq.offer(new Destination(next, cost));
	                }
	            }
	        }
	    }
	}

 
	static void changeDepart() {
		dijkstra();
		List<Tour> list = new ArrayList<>();
		 while (!tour.isEmpty()) {
			Tour tmp = tour.poll();
			list.add(tmp);
	     }
		 
		 tour.clear();
		 for (Tour t : list) {
			 tour.offer(t);
		 }
		
	}
}
