package Graphs.UndirectedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Graph {
	private final int V; // 顶点数目
	private int E; // 边的数目
	private Bag<Integer>[] adj; // 邻接表
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for(int v = 0; v < G.V(); v++) {
			Stack<Integer> stack = new Stack<Integer>(); // 使用栈来存G.adj[v]
			for(int w : adj[v]) {
				stack.push(w);
			}
			for(int w : stack) {
				adj[v].add(w);
			}
		}
		
	}
	public boolean hasEdge(int v, int w) {
		for(int i : adj(v)) {
			if(i == w) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		// 使用 StringBuilder
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges \n");
		for(int v = 0; v < V; v++) {
			s.append(v + ": ");
			for(int w : adj[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		System.out.println(g);
	}
}
