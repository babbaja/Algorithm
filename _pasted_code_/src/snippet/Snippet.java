package snippet;

public class Snippet {
	static int minDistance(int depart, int dest) {
	    int n = land.size(); // 노드 개수
	    int[] dist = new int[n];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[depart] = 0;
	
	    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
	    pq.offer(new int[] {depart, 0});
	
	    while (!pq.isEmpty()) {
	        int[] cur = pq.poll();
	        int now = cur[0];
	        int cost = cur[1];
	
	        if (now == dest) return cost; // 도착하면 바로 리턴 가능
	
	        if (dist[now] < cost) continue; // 이미 더 짧은 경로로 방문한 적 있으면 스킵
	
	        for (Destination next : land.get(now)) {
	            int to = next.to;
	            int weight = next.weight;
	
	            if (dist[to] > dist[now] + weight) {
	                dist[to] = dist[now] + weight;
	                pq.offer(new int[] {to, dist[to]});
	            }
	        }
	    }
	
	    return -1; // 도달할 수 없는 경우
	}
	
}

