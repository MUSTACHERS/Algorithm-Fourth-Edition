package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;  // 所有顶点的前序排列
	private Queue<Integer> post; // 所有顶点的后序排序
	private Stack<Integer> reversePost; // 所有顶点的逆后序排序
	public DepthFirstOrder(EdgeWeightedDigraph G) {
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(! marked[v]) {
				dfs(G, v);
			}
		}
	}
	private void dfs(EdgeWeightedDigraph G, int v) {
		pre.enqueue(v);
		marked[v] = true;
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(! marked[w]) {
				dfs(G, w);
			}
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre() {
		return pre;
	}
	public Iterable<Integer> post() {
		return post;
	}
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
