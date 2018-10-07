package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.Queue;

public class BellmanFordSP {
	private double[] distTo; // 从起点到某个顶点的路径长度
	private DirectedEdge[] edgeTo; // 从起点到某个顶点的最后一条边
	private boolean[] onQ; // 该顶点是否存在于队列中
	private Queue<Integer> queue; // 正在被放松的顶点；
	private int cost; // relax()的调用次数
	private Iterable<DirectedEdge> cycle; // edgeTo[]中的是否有负权重的环
	
	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		queue = new Queue<Integer>();
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		queue.enqueue(s);
		onQ[s] = true; // 入列就标记为true
		while(! queue.isEmpty() && ! hasNegativeCycle()) {
			int v = queue.dequeue();
			onQ[v] = false; // 出列就标记为false
			relax(G, v);
		}
	}
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(! onQ[w]) { // 判断w是否在队列中
					queue.enqueue(w);
					onQ[w] = true; // 入列标记
				}
			}
			if(cost++ % G.V() == 0) { // 每调用V次relax()方法后
				findNegativeCycle(); // 检查下是否有负权重的环
			}
		}
	}
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt;
		spt = new EdgeWeightedDigraph(V);
		for(int v = 0; v < V; v++) {
			if(edgeTo[v] != null) {
				spt.addEdge(edgeTo[v]);
			}
		}
		EdgeWeightedCycleFinder cf;
		cf = new EdgeWeightedCycleFinder(spt);
		cycle = cf.cycle();
	}
	
	public boolean hasNegativeCycle() {
		return cycle != null;
	}
	public Iterable<DirectedEdge> negativeCycle() {  
		return cycle;
	}
}
