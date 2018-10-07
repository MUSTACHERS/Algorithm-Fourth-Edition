package Graphs.ShortestPaths;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDigraph {
	private final int V; // 顶点总数
	private int E; // 边的总数
	private Bag<DirectedEdge>[] adj; // 邻接表
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			DirectedEdge de = new DirectedEdge(v, w, weight);
			addEdge(de);
		}
	}
	public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
		this(G.V());
		this.E = G.E();
		for(int v = 0; v < G.V(); v++) {
			Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
			for(DirectedEdge e : G.adj(v)) {
				reverse.push(e);
			}
			for(DirectedEdge e : reverse) {
				adj[v].add(e);
			}
		}
	}
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++) {
			for(DirectedEdge e : adj[v]) {
				bag.add(e);
			}
		}
		return bag;
	}
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append(V + " vertex, " + E + " edges.\n");
		for(int v = 0; v < V; v++) {
			st.append(v + ": ");
			for(DirectedEdge w : adj[v]) {
				st.append(w + " ");
			}
			st.append("\n");
		}
		return st.toString();
	}
}
