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
	private DirectedEdge[] edgeTo; // 存储路径
	private double[] distTo; // s->w 的距离
	private IndexMinPQ<Double> pq; // 保存需要被放松的顶点并确定下一个放松的顶点
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY; // 全部初始化为 无穷大
		}
		distTo[s] = 0.0; // 先把起点的distTo初始化为0.0
		pq.insert(s, 0.0); // 插入
		while(! pq.isEmpty()) {
			relax(G, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v) { // 放松顶点
//		为了检查v->w 是否是 从 起点到v，再从v到w。
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to(); // v的另一顶点
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
	public double distTo(int v) { // 得到起点到v的距离
		return distTo[v];
	}
	public boolean hasPathTo(int v) { // 直接检查distTo是否是无穷大来判断是否有可达v的路径
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v) {
		if(! hasPathTo(v)) { // 是否有达到v的路径
			return null;
		}
		Stack<DirectedEdge> path = new Stack<DirectedEdge>(); 
		// 用栈来存储，存储时从达到的路径到起点入栈，打印出来是从起点到要达到的路径
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) { // e.from() 另一顶点
			path.push(e);
		}
		return path;
	}
	
	public static void main(String[] args) {
		EdgeWeightedDigraph G;
		G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DijkstraSP sp = new DijkstraSP(G, s);
		
		for(int t = 0; t < G.V(); t++) { // 到任意顶点
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
