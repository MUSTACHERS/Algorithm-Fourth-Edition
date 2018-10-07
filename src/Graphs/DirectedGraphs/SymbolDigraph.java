package Graphs.DirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolDigraph {
	private ST<String, Integer> st; // 符号名 ——> 索引
	private String[] keys; // 索引 ——> 符号名
	private Digraph G; // 图
	public SymbolDigraph(String stream, String sp) {
		st = new ST<String, Integer>();
		In in = new In(stream); // 第一遍
		while(in.hasNextLine()) { // 构造索引
			String[] a = in.readLine().split(sp); // 读取字符串
			for(int i = 0; i < a.length; i++) { // 为每个不同的字符串关联一个索引
				if(! st.contains(a[i])) {
					st.put(a[i], st.size());
				} 
			}
		}
		keys = new String[st.size()]; // 用来获得顶点名的反向索引的一个数组
		for(String name : st.keys()) {
			keys[st.get(name)] = name; // 用对应的索引来存储对应的顶点名
		}
		G = new Digraph(st.size()); // 构造图
		in = new In(stream); // 第二遍遍历
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i = 1; i < a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s) {
		return st.contains(s);
	}
	public int index(String s) {
		return st.get(s);
	}
	public String name(int v) {
		return keys[v];
	}
	public Digraph G() {
		return G;
	}
}

