package Graphs.UndirectedGraph;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(! marked[s]) {
				dfs(G, s, s); // 参数再带一个起来，用来比较
			}
		}
	}
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(! marked[w]) {
				dfs(G, w, v);
			} else if(w != u) { 
				hasCycle = true; // 有环
			}
		}
	}
	public boolean hasCycle() {
		return hasCycle();
	}
}
