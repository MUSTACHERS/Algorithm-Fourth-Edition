package Searching.Application;

import Searching.BalanceSearchTree.RedBlackTree;

/**
 * Prac_3_5_5
 * @author MUSTACHE
 *
 */
public class STdouble <Value> {
	private RedBlackTree<Double, Value> st;
	public STdouble() {
		st = new RedBlackTree<Double, Value>();
	}
	public void put(double key, Value val) {
		st.put(key, val);
	}
	public Value get(double key) {
		return st.get(key);
	}
	public void delete(double key) {
		st.delete(key);
	}
	public boolean contains(double key) {
		return st.contains(key);
	}
	public boolean isEmpty() {
		return st.isEmpty();
	}
	public int size() {
		return st.size();
	}
	public double max() {
		return st.max();
	}
	public double min() {
		return st.min();
	}
	public Iterable<Double> keys() {
		return st.keys();
	}
}
