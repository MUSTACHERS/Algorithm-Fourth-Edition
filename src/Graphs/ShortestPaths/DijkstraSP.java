package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/**
 * @author MUSTACHE
 * 
 * % java DijkstraSP tinyEWD.txt 0
 * 0 to 0 (0.00): 
 * 0 to 1 (1.05): 0->4 0.38  4->5 0.35  5->1 0.32  
 * 0 to 2 (0.26): 0->2 0.26  
 * 0 to 3 (0.99): 0->2 0.26  2->7 0.34  7->3 0.39  
 * 0 to 4 (0.38): 0->4 0.38  
 * 0 to 5 (0.73): 0->4 0.38  4->5 0.35  
 * 0 to 6 (1.51): 0->2 0.26  2->7 0.34  7->3 0.39  3->6 0.52  
 * 0 to 7 (0.60): 0->2 0.26  2->7 0.34  
 * 
 */
public class DijkstraSP {
	private DirectedEdge[] edgeTo; // �洢·��
	private double[] distTo; // s->w �ľ���
	private IndexMinPQ<Double> pq; // ������Ҫ�����ɵĶ��㲢ȷ����һ�����ɵĶ���
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY; // ȫ����ʼ��Ϊ �����
		}
		distTo[s] = 0.0; // �Ȱ�����distTo��ʼ��Ϊ0.0
		pq.insert(s, 0.0); // ����
		while(! pq.isEmpty()) {
			relax(G, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v) { // ���ɶ���
//		Ϊ�˼��v->w �Ƿ��� �� ��㵽v���ٴ�v��w��
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to(); // v����һ����
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(pq.contains(w)) {
					pq.change(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	public double distTo(int v) { // �õ���㵽v�ľ���
		return distTo[v];
	}
	public boolean hasPathTo(int v) { // ֱ�Ӽ��distTo�Ƿ�����������ж��Ƿ��пɴ�v��·��
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v) {
		if(! hasPathTo(v)) { // �Ƿ��дﵽv��·��
			return null;
		}
		Stack<DirectedEdge> path = new Stack<DirectedEdge>(); 
		// ��ջ���洢���洢ʱ�Ӵﵽ��·���������ջ����ӡ�����Ǵ���㵽Ҫ�ﵽ��·��
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) { // e.from() ��һ����
			path.push(e);
		}
		return path;
	}
	
	public static void main(String[] args) {
		EdgeWeightedDigraph G;
		G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DijkstraSP sp = new DijkstraSP(G, s);
		
		for(int t = 0; t < G.V(); t++) { // �����ⶥ��
			StdOut.print(s + " to " + t);
			StdOut.printf(" (%4.2f): ", sp.distTo(t)); 
			if(sp.hasPathTo(t)) {
				for(DirectedEdge e : sp.pathTo(t)) {
					StdOut.print(e + "  ");
				}
			}
			StdOut.println();
		}
	}
}
