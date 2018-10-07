package Graphs.UndirectedGraph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE; // 用于distTo()
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	private int[] distTo;
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;  // 标记起点
		for(int i = 0; i < G.V(); i++) {
			distTo[i] = INFINITY;  // 不懂为什么要用整数的最大值来初始化
		}
		distTo[s] = 0; // 起点的长度初始化为0
		queue.enqueue(s); // 将它加入队列
		while(! queue.isEmpty()) { 
			int v = queue.dequeue(); // 从队列中中取下一顶点
			for(int w : G.adj(v)) {
				if(! marked[w]) { // 对于每个未被标记的相邻顶点
					edgeTo[w] = v; // 保存最短路径的最后一条边
					distTo[w] = distTo[v] + 1;
					marked[w] = true; // 标记它
					queue.enqueue(w); // 入列
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v) {
		if(! hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
	
	public int distTo(int v) {
		return distTo[v];
	}
}
