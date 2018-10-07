package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.Queue;

public class BellmanFordSP {
	private double[] distTo; // ����㵽ĳ�������·������
	private DirectedEdge[] edgeTo; // ����㵽ĳ����������һ����
	private boolean[] onQ; // �ö����Ƿ�����ڶ�����
	private Queue<Integer> queue; // ���ڱ����ɵĶ��㣻
	private int cost; // relax()�ĵ��ô���
	private Iterable<DirectedEdge> cycle; // edgeTo[]�е��Ƿ��и�Ȩ�صĻ�
	
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
		onQ[s] = true; // ���оͱ��Ϊtrue
		while(! queue.isEmpty() && ! hasNegativeCycle()) {
			int v = queue.dequeue();
			onQ[v] = false; // ���оͱ��Ϊfalse
			relax(G, v);
		}
	}
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(! onQ[w]) { // �ж�w�Ƿ��ڶ�����
					queue.enqueue(w);
					onQ[w] = true; // ���б��
				}
			}
			if(cost++ % G.V() == 0) { // ÿ����V��relax()������
				findNegativeCycle(); // ������Ƿ��и�Ȩ�صĻ�
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
