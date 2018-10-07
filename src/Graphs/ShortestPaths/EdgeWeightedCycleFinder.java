package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {
		private boolean[] marked;
		private DirectedEdge[] edgeTo;
		private Stack<DirectedEdge> cycle; // 有向环中的所有顶点（如果存在）
		private boolean[] onStack;
		
		public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
			marked = new boolean[G.V()];
			edgeTo = new DirectedEdge[G.V()];
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
					edgeTo[w] = e;
					dfs(G, w);
				} else if(onStack[w]) {
					cycle = new Stack<DirectedEdge>();
					for(DirectedEdge i = edgeTo[w]; i != null; i = edgeTo[e.from()]) {
						cycle.push(i);
					}
					cycle.push(edgeTo[w]);
				}
			}
			onStack[v] = false; 
		}
		public boolean hasCycle() {
			return cycle != null;
		}
		public Iterable<DirectedEdge> cycle() {
			return cycle;
		}
	}
