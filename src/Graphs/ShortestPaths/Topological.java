package Graphs.ShortestPaths;

import Graphs.DirectedGraphs.Digraph;
import Graphs.DirectedGraphs.DirectedCycle;
import edu.princeton.cs.algs4.StdOut;

public class Topological {
	private Iterable<Integer> order; // 顶点的拓扑排序
	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDigraphCycle cyclefinder = new EdgeWeightedDigraphCycle(G); // 用来检测是否是有向环
		if(! cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G); // 深度搜索
			order = dfs.reversePost(); // 逆后序
		}
	}
	public Iterable<Integer> order() {
		return order;
	}
	public boolean isDAG() {
		return order != null;
	}
	
}
