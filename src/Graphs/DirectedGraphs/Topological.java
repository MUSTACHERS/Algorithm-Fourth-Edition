package Graphs.DirectedGraphs;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author MUSTACHE
 * 
 * %java Topological jobs.txt "/"
 * Calculus
 * Linear Algebra
 * Introduction to CS
 * Advanced Programming
 * Algorithms
 * Theoretical CS
 * Artificial Intelligence
 * Robotics
 * Machine Learning
 * Neural Networks
 * Databases
 * Scientific Computing
 * Computational Biology
 */
public class Topological {
	private Iterable<Integer> order; // �������������
	public Topological(Digraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G); // ��������Ƿ�������
		if(! cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G); // �������
			order = dfs.reversePost(); // �����
		}
	}
	public Iterable<Integer> order() {
		return order;
	}
	public boolean isDAG() {
		return order != null;
	}
	
	public static void main(String[] args) {
		String filename = args[0];
		String separator = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);
		Topological top = new Topological(sg.G());
		for(int v : top.order()) {
			StdOut.println(sg.name(v));
		}
	}
}
