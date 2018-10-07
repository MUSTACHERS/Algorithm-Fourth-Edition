package Graphs.DirectedGraphs;

public class KosarajuSCC {
	private boolean[] marked; // 已访问过的顶点
	private int[] id; // 强连通分量的标识符
	private int count; // 强连通分量的数量
	
	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reserve());// 使用图的反向图
		for(int s : order.reversePost()) { // 使用反响图的逆后序
			if(! marked[s]) {
				dfs(G, s);
				count++; // 每调用一次深度搜索就都处于同一个强连通分量中
			}
		}
	}
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count; // 记录强连通分量
		for(int w : G.adj(v)) {
			if(! marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	public int id(int v) {
		return id[v];
	}
	public int count() { // 有多少个强连通分量
		return count;
	}
}
