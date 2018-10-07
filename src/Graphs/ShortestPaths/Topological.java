package Graphs.ShortestPaths;

import Graphs.DirectedGraphs.Digraph;
import Graphs.DirectedGraphs.DirectedCycle;
import edu.princeton.cs.algs4.StdOut;

public class Topological {
	private Iterable<Integer> order; // �������������
	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDigraphCycle cyclefinder = new EdgeWeightedDigraphCycle(G); // ��������Ƿ�������
		if(! cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G); // �������
			order = dfs.reversePost(); // �����
		}
	}
	public Iterable<Integer> order() {
		return order;
	}
	public boolean isDAG() {
		return order != null;
	}
	
}
