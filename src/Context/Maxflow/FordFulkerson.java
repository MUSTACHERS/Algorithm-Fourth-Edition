package Context.Maxflow;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author MUSTACHE
 *
 * % java FordFulkerson tinyFN.txt
 *  Max flow from 0 to 5
 * 0->2 3.00 2.00
 * 0->1 2.00 2.00
 * 1->4 1.00 1.00
 * 1->3 3.00 1.00
 * 2->4 1.00 1.00
 * 2->3 1.00 1.00
 * 3->5 2.00 2.00
 *  4->5 3.00 2.00
 * Max flow value = 4.0
 */
public class FordFulkerson {
	private boolean[] marked; // ��ʣ���������Ƿ���ڴ�s��v��·����
	private FlowEdge[] edgeTo; // ��s��v�����·���ϵ����һ����
	private double value; // ��ǰ�������
	
	public FordFulkerson(FlowNetwork G, int s, int t) {
		// �ҳ���s��t����������G�������������
		while(hasAugmentingPath(G, s, t)) {
			// �������Դ��ڵ�����·��
			// ���㵱ǰ��ƿ������
			double bottle = Double.POSITIVE_INFINITY;
			for(int v = t; v != s; v = edgeTo[v].other(v)) {
				// �����·���ϵ����б���δʹ����������Сֵ
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			}
			for(int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}
			value += bottle;
		}
	}
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
		marked = new boolean[G.V()]; // ���·����֪�Ķ���
		edgeTo = new FlowEdge[G.V()]; // ·���ϵ����һ����
		Queue<Integer> q = new Queue<Integer>();
		
		marked[s] = true; // ������
		q.enqueue(s); // ����������
		while(! q.isEmpty()) {
			int v = q.dequeue();
			for(FlowEdge e:G.adj(v)) {
				int w = e.other(v);
				if(e.residualCapacityTo(w) > 0 && ! marked[w]) {
					// (��ʣ��������)��������һ�����ӵ�һ��δ��ǵĶ���ı�
					edgeTo[w] = e; // ����·���ϵ����һ����
					marked[w] = true; // ���w����Ϊ·����������֪����
					q.enqueue(w); // ��������
				}
			}
		}
		return marked[t];
	}
	public double value() { return value; }
	public boolean inCut(int v) { return marked[v]; }
	
	public static void main(String[] args) {
		FlowNetwork G = new FlowNetwork(new In(args[0]));
		int s = 0, t = G.V() - 1;
		FordFulkerson maxflow = new FordFulkerson(G, s, t);
		StdOut.println("Max flow from " + s + " to " + t);
		for(int v = 0; v < G.V(); v++) {
			for(FlowEdge e : G.adj(v)) {
				if((v == e.from()) && e.flow() > 0) {
					StdOut.println("  " + e);
				}
			}
		}
		StdOut.println("Max flow value = " + maxflow.value());		
	}
}
