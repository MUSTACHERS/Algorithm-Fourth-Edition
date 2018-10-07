package Graphs.MinSpanningTrees;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
	private boolean[] marked; // ��С�������Ķ���
	private Queue<Edge> mst; // ��С�������ı�
	private MinPQ<Edge> pq; // ���бߣ������Ѿ�ʧЧ�ıߣ�
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		visit(G, 0); // ����G����ͨ��
		while(! pq.isEmpty()) {
			Edge e = pq.delMin(); // �Ӻ����еõ�Ȩ����С�ı�
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w]) { // �����Ѿ�ʧЧ�ı�
				continue;
			}
			mst.enqueue(e); // ������ӵ�����
			if(! marked[v]) { // ������v/w ��ӵ�����
				visit(G, v);
			}
			if(! marked[w]) {
				visit(G, w);
			}
		}
	}
	private void visit(EdgeWeightedGraph G, int v) {
//		��Ƕ���v������������v��δ����Ƕ���ı߼���pq
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			if(! marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}
	public Iterable<Edge> edges() {
		return mst;
	}
}
