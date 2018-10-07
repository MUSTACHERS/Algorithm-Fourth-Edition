package Graphs.ShortestPaths;

import Graphs.DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDigraphCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle; // 有向环中的所有顶点（如果存在）
	private boolean[] onStack;
	
	public EdgeWeightedDigraphCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(! marked[v]) {
				dfs(G, v);
			}
		}
	}
	private void dfs(EdgeWeightedDigraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(this.hasCycle()) {
				return ;
			} else if(! marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			} else if(onStack[w]) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v); // 最后把起点再次push进去，形成环
			}
		}
		onStack[v] = false; 
	}
	public boolean hasCycle() {
		return cycle != null;
	}
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
