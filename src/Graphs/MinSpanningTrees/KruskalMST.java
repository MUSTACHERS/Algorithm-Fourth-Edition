package Graphs.MinSpanningTrees;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
	private Queue<Edge> mst;
	private double weight;
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(G.V());
		while(! pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin(); // 从pq得到权重最小的边和它的顶点
			int v = e.either(), w = e.other(v);
			if(uf.connected(v, w)) { // 忽略失效的边
				continue; 
			}
			uf.union(v, w); // 合并分量
			mst.enqueue(e); // 将边添加到最小生成树中
			weight += e.weight();
		}
	}
	public Iterable<Edge> edges(){
		return mst;
	}
	public double weight() {
		return weight;
	}
}
