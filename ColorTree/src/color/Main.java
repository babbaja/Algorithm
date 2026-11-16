package color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int mid;
	int pid;
	int color;
	int maxdepth;
	Node parent;
	List<Node> son = new LinkedList<>();
	
	public Node(int mid, int pid, int color, int maxdepth) {
		this.mid = mid;
		this.pid = pid;
		this.color = color;
		this.maxdepth = maxdepth;
	}
}

public class Main {
	static List<Node> root = new LinkedList<>();
	static int[] score = new int[100001];
	static int[] colorArr = new int[100001];
	static Node[] nodes = new Node[100001];
	static Map<Integer, Node> nodeMap = new HashMap<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if (num == 100) {
				int mid = Integer.parseInt(st.nextToken());
				int pid = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				int depth  = Integer.parseInt(st.nextToken());
				addNode(mid, pid, color, depth);
			}
			else if (num == 200) {
				int mid = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				Node node = nodeMap.get(mid);
				colorChange(node, color);
			}
			else if (num == 300) {
				int target = Integer.parseInt(st.nextToken());
				System.out.println(colorArr[target]);
			}
			else if (num == 400) {
				Arrays.fill(score, 0);
				for (Node r : root) dfs(r);
				int totalScore = 0;
				for (int i = 0; i < score.length; ++i) totalScore += (score[i] * score[i]);
				System.out.println(totalScore);
			}
		}
		//printNode();
	}
	
	static void printNode() {
		Queue<Node> queue = new LinkedList<>();
		for (int i = 0; i < root.size(); ++i) queue.offer(root.get(i));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			//System.out.println(cur.mid + " " + cur.pid  + " " + cur.color  + " " + cur.maxdepth);
			
			for (int i = 0; i < cur.son.size(); ++i) {
				queue.offer(cur.son.get(i));
			}
		}
		
	}
	
	static void addNode(int mid, int pid, int color, int depth) {
	    Node node = new Node(mid, pid, color, depth);
	    nodeMap.put(mid, node);

	    if (pid == -1) {
	        root.add(node);
	        node.parent = null;
	        colorArr[node.mid] = node.color;
	        nodes[node.mid] = node;
	    } else {
	        Node parent = nodeMap.get(pid); // 수정된 부분
	        node.parent = parent;
	        if (depthCheck(node)) {
	            parent.son.add(node);
	            colorArr[node.mid] = node.color;
	            nodes[node.mid] = node;
	        }
	    }
	}
	
	static void colorChange(Node node, int color) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			cur.color = color;
			colorArr[cur.mid] = color;
			
			for (int i = 0; i < cur.son.size(); ++i) {
				queue.offer(cur.son.get(i));
			}
		}
	}
	
	static boolean depthCheck(Node node) {
		int idx = 1;
		Node next = node.parent;
		
		while (next != null) {
			idx += 1;
			
			//System.out.println(next.mid + " " + next.maxdepth + " " + idx);
			if (next.maxdepth < idx) return false;
			next = next.parent;
		}
		
		
		return true;
	}

	static HashSet<Integer> dfs(Node node) {
		HashSet<Integer> colorSet = new HashSet<>();
		for (Node child : node.son) {
			HashSet<Integer> childSet = dfs(child);
			if (childSet.size() > colorSet.size()) {
				HashSet<Integer> temp = colorSet;
				colorSet = childSet;
				colorSet.addAll(temp);
			} 
			else {
				colorSet.addAll(childSet);
			}
		}

		colorSet.add(node.color);
		score[node.mid] = colorSet.size();
		return colorSet;
	}

	
}
