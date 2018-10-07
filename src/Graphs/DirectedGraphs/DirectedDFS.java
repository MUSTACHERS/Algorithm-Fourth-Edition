package Graphs.DirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author MUSTACHE
 * 
 * %java DirectedDFS tinyDG.txt 1
 * 1
 * 
 * %java DirectedDFS tinyDG.txt 2
 * 0 1 2 3 4 5
 * 
 * %java DirectedDFS tinyDG.txt 1 2 6
 * 0 1 2 3 4 5 6 9 10 11 12
 * 
 */
public class DirectedDFS {
	private boolean[] marked;
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for(int s : sources) {
			if(! marked[s]) {
				dfs(G, s);
			}
		}
	}
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(! marked[w]) {
				dfs(G, w);
			}
		}
	}
	public boolean marked(int v) {
		return marked[v];
	}
	
	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		Bag<Integer> sources = new Bag<Integer>();
		for(int i = 1; i < args.length; i++) {
			sources.add(Integer.parseInt(args[i]));
		}
		DirectedDFS reachable = new DirectedDFS(G, sources);
		for(int i = 0; i < G.V(); i++) {
			if(reachable.marked[i]) {
				StdOut.print(i + " ");
			}
		}
		StdOut.println();
	}
}
