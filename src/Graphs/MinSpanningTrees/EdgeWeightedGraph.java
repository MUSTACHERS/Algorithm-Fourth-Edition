package Graphs.MinSpanningTrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedGraph {
	private final int V; // 顶点总数
	private int E; // 边的总数
	private Bag<Edge>[] adj; // 邻接表
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readInt();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	public EdgeWeightedGraph(EdgeWeightedGraph G) {
		this(G.V());
		this.E = G.E;
		for(int v = 0; v < G.V(); v++) {
			Stack<Edge> reverse = new Stack<Edge>();
			for(Edge e : G.adj(v)) {
				reverse.push(e);
			}
			for(Edge e : reverse) {
				adj[v].add(e);
			}
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(Edge e) {
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	public Iterable<Edge> edges() {
		Bag<Edge> bag = new Bag<Edge>();
		for(int v = 0; v < V; v++) {
			for(Edge e : adj[v]) {
				bag.add(e);
			}
		}
		return bag;
	}
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append(V + " vertex, " + E + " edges\n");
		for(int v = 0; v < V; v++) {
			st.append(v + ":");
			for(Edge e : adj[v]) {
				st.append(e + " ");
			}
			st.append("\n");
		}
		return st.toString();
	}
}
