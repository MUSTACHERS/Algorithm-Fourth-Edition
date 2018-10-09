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
			Edge e = pq.delMin(); // ��pq�õ�Ȩ����С�ıߺ����Ķ���
			int v = e.either(), w = e.other(v);
			if(uf.connected(v, w)) { // ����ʧЧ�ı�
				continue; 
			}
			uf.union(v, w); // �ϲ�����
			mst.enqueue(e); // ������ӵ���С��������
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
