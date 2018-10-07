package Graphs.UndirectedGraph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE; // ����distTo()
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
		marked[s] = true;  // ������
		for(int i = 0; i < G.V(); i++) {
			distTo[i] = INFINITY;  // ����ΪʲôҪ�����������ֵ����ʼ��
		}
		distTo[s] = 0; // ���ĳ��ȳ�ʼ��Ϊ0
		queue.enqueue(s); // �����������
		while(! queue.isEmpty()) { 
			int v = queue.dequeue(); // �Ӷ�������ȡ��һ����
			for(int w : G.adj(v)) {
				if(! marked[w]) { // ����ÿ��δ����ǵ����ڶ���
					edgeTo[w] = v; // �������·�������һ����
					distTo[w] = distTo[v] + 1;
					marked[w] = true; // �����
					queue.enqueue(w); // ����
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
