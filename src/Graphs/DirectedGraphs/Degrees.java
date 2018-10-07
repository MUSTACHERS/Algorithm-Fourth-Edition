package Graphs.DirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/*
 * @author MUSTACHE
 *
 */
public class Degrees {
	private int[] indegree;
	private int[] outdegree;
	private int count;
	public Degrees(Digraph G) {
		indegree = new int[G.V()];
		outdegree = new int[G.V()];
		for(int v = 0; v < G.V(); v++) {
			dfs(G, v);
			bfs(G.reserve(), v);
		}
		
	}
	private void dfs(Digraph G, int v) { // 计算出度
		count = 0;
		for(int w : G.adj(v)) {
			count++;
		}
		outdegree[v] = count;
	}
	private void bfs(Digraph G, int v) { //  计算入度
		count = 0; 
		for(int w : G.adj(v)) {
			count++;
		}
		indegree[v] = count;
	}
	public int indegree(int v) {
		return indegree[v];
	}
	public int outdegree(int v) {
		return outdegree[v];
	}
	public Iterable<Integer> sources() {
		Queue<Integer> queue = new Queue<Integer>();
		for(int i = 0; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.enqueue(i);
			}
		}
		return queue;
	}
	public Iterable<Integer> sinks() {
		Queue<Integer> queue = new Queue<Integer>();
		for(int i = 0; i < outdegree.length; i++) {
			if(outdegree[i] == 0) {
				queue.enqueue(i);
			}
		}
		return queue;
	}
	public boolean isMap() {
		for(int i = 0; i < outdegree.length; i++) {
			if(outdegree[i] != 1) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		System.out.println(G);
		System.out.println("图取反：");
		System.out.println(G.reserve());
		Degrees ds = new Degrees(G);
		System.out.println("出度：" + ds.outdegree(6));
		System.out.println("入度：" + ds.indegree(7));
	}
}
