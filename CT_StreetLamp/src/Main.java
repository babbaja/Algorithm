import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int l, r, d, s;

        public Node(int l, int r, int d, int s) {
            this.l = l;
            this.r = r;
            this.d = d;
            this.s = s;
        }

        @Override
        public int compareTo(Node o1) {
            if (this.d == o1.d) return this.s - o1.s;
            return o1.d - this.d;
        }
    }
    static int n, m;
    static int s, e;
    static Map<Integer, Integer> left;
    static Map<Integer, Integer> right;
    static Map<Integer, Integer> map;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        left = new HashMap<>();
        right = new HashMap<>();
        map = new HashMap<>();
        pq = new PriorityQueue<>();
        int Q = Integer.parseInt(st.nextToken());

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) {
                init(st);
            }
            else if (command == 200) {
                addLamp();
            }
            else if (command == 300) {
                int d = Integer.parseInt(st.nextToken());
                delete(d);
            }
            else {
                lampPower();
            }
        }
    }
    static void lampPower() {
        Node curr = safeNode();
        
        int sEdge = map.get(s) - 1;
        int eEdge = n - map.get(e);
        int dist = 0;
        
        if (curr != null) dist = curr.d;
        
        System.out.println(Math.max(dist, Math.max(eEdge * 2, sEdge * 2)));
        pq.offer(curr);
    }
    

    static void delete(int d) {
        if (right.containsKey(d) && left.containsKey(d)) {
            int l = left.get(d);
            int r = right.get(d);

            map.remove(d);
            left.remove(d);
            right.remove(d);

            left.put(r, l);
            right.put(l, r);

            pq.offer(new Node(l, r, distance(l , r), map.get(l)));
        }
        else if (left.containsKey(d)) { // e
            int l = left.get(d);
            e = l;
            
            map.remove(d);
            left.remove(d);
            right.remove(l);
        }
        else if (right.containsKey(d)) { // s
            int r = right.get(d);
            s = r;
            
            map.remove(d);
            right.remove(d);
            left.remove(r);
        }
    }

    static void addLamp() {
        Node cur = safeNode();
        if (cur == null) return;

        int mid = (map.get(cur.l) + map.get(cur.r) + 1) / 2;
        m++;

        left.put(m, cur.l);
        right.put(cur.l, m);
        right.put(m, cur.r);
        left.put(cur.r, m);
        map.put(m, mid);

        pq.offer(new Node(cur.l, m, distance(cur.l, m), map.get(cur.l)));
        pq.offer(new Node(m, cur.r, distance(cur.r, m), map.get(m)));
    }

    static Node safeNode() {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (left.containsKey(cur.r) && right.containsKey(cur.l)) {
                if (left.get(cur.r) == cur.l && right.get(cur.l) == cur.r)
                    return cur;
            }
        }
        return null;
    }

    static void init(StringTokenizer st) {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        s = 1;
        e = m;

        for (int i = 1; i <= m; i++) {
            int loc = Integer.parseInt(st.nextToken());
            map.put(i, loc);
            if (i != 1) left.put(i, i - 1);
            if (i != m) right.put(i, i + 1);
        }

        for (int i = 1; i < m; i++) {
            pq.offer(new Node(i, i + 1, distance(i, i + 1), map.get(i)));
        }
    }

    static int distance(int l, int r) {
        return Math.abs(map.get(l) - map.get(r));
    }
}