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
	private boolean[] marked; // 在剩余网络中是否存在从s到v的路径？
	private FlowEdge[] edgeTo; // 从s到v的最短路径上的最后一条边
	private double value; // 当前最大流量
	
	public FordFulkerson(FlowNetwork G, int s, int t) {
		// 找出从s到t的流量网络G的最大流量配置
		while(hasAugmentingPath(G, s, t)) {
			// 利用所以存在的增广路径
			// 计算当前的瓶颈容量
			double bottle = Double.POSITIVE_INFINITY;
			for(int v = t; v != s; v = edgeTo[v].other(v)) {
				// 计算该路径上的所有边中未使用容量的最小值
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			}
			for(int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}
			value += bottle;
		}
	}
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
		marked = new boolean[G.V()]; // 标记路径已知的顶点
		edgeTo = new FlowEdge[G.V()]; // 路径上的最后一条边
		Queue<Integer> q = new Queue<Integer>();
		
		marked[s] = true; // 标记起点
		q.enqueue(s); // 并把它入列
		while(! q.isEmpty()) {
			int v = q.dequeue();
			for(FlowEdge e:G.adj(v)) {
				int w = e.other(v);
				if(e.residualCapacityTo(w) > 0 && ! marked[w]) {
					// (在剩余网络中)对于任意一条连接到一个未标记的顶点的边
					edgeTo[w] = e; // 保存路径上的最后一条边
					marked[w] = true; // 标记w，因为路径现在是已知的了
					q.enqueue(w); // 将它入列
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
