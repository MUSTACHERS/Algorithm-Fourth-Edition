package Graphs.DirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Digraph {
	private int E;
	private int V;
	private Bag<Integer>[] adj; 
	public Digraph(int V) {
		this.E = 0;
		this.V = V;
		adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	public Digraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	public Digraph(Digraph G) {
		this(G.V());
		this.E = G.E();
		for(int v = 0; v < G.V(); v++) {
			Stack<Integer> stack = new Stack<Integer>();
			for(int w : adj(v)) {
				stack.push(w);
			}
			for(int w : stack) {
				adj[v].add(w);;
			}
		}
	}
	public int E() {
		return E;
	}
	public int V() {
		return V;
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);  // v -> w
		E++;
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	public Digraph reserve() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append(V + " vertices, " + E + " edges \n");
		for(int v = 0; v < V; v++) {
			st.append(v + ":");
			for(int w : adj(v)) {
				st.append(w + " ");
			}
			st.append("\n");
		}
		return st.toString();
	}
	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		System.out.println(G);
		System.out.println(G.reserve());
	}
}
