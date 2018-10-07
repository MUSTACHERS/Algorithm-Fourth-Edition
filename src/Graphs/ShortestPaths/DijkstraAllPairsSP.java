package Graphs.ShortestPaths;

public class DijkstraAllPairsSP {
	private DijkstraSP[] all; // �洢���
	public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
		all = new DijkstraSP[G.V()];
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DijkstraSP(G, v);
		}
	}
//  ���ⶥ���֮������·��
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}
//	���ⶥ���֮��ľ���
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
