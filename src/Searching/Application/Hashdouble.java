package Searching.Application;

import edu.princeton.cs.algs4.LinearProbingHashST;

/**
 * Practice 3_5_4
 * @author MUSTACHE
 *
 * @param <Value>
 */
public class Hashdouble <Value>{
	private LinearProbingHashST<Double, Value> st;
	public Hashdouble() {
		st = new LinearProbingHashST<Double, Value>();
	}
	public void put(double key,Value val) {
		st.put(key, val);
	}
	public Value get(double key) {
		return st.get(key);
	}
	public boolean isEmpty() {
		return st.isEmpty();
	}
	public boolean contains(double key) {
		return st.contains(key);
	}
	public int size() {
		return st.size();
	}
	public void delete(double key) {
		st.delete(key);
	}
	public Iterable<Double> keys() {
		return st.keys();
	}
}
