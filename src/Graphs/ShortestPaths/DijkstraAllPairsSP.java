package Graphs.ShortestPaths;

public class DijkstraAllPairsSP {
	private DijkstraSP[] all; // 存储起点
	public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
		all = new DijkstraSP[G.V()];
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DijkstraSP(G, v);
		}
	}
//  任意顶点对之间的最短路径
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}
//	任意顶点对之间的距离
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
