package Searching.Application;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
 /**
  * 
  * @author MUSTACHE
  * a[][]:	0.0 0.90 0.0 0.0 0.0
  *			0.0 0.0 0.36 0.36 0.18
  *			0.0 0.0 0.0 0.90 0.0
  *			0.90 0.0 0.0 0.0 0.0
  *			0.45 0.0 0.45 0.0 0.0
  * x[]: .05
  * 	 .04
  * 	 .36
  * 	 .37
  *  	 .19
  *  
  *  b[]: 	0.036000000000000004
  *			0.297
  *			0.333
  *			0.045000000000000005
  *			0.1845
  *
  */
public class SparseVector {
	private LinearProbingHashST<Integer, Double> st; // 存储向量
	public SparseVector() {
		st = new LinearProbingHashST<Integer, Double>();
	}
	public int size() {
		return st.size();
	}
	public void put(int i, double x) {
		st.put(i, x);
	}
	public double get(int i) {
		if(! st.contains(i)) {
			return 0.0;
		} else {
			return st.get(i);
		}
	}
	public double dot(double[] that) {
		double sum = 0.0;
		for(int i : st.keys()) {
			sum += that[i] * this.get(i); // 将一个向量中的每一项和另一个向量中对应项相乘并将所有结果相加
		}
		return sum;
	}
	
	public static void main(String[] args) {
		SparseVector[] spa = new SparseVector[5];
		double[] x = new double[] {.05, .04, .36, .37, .19}; // 向量
		double[] b = new double[5];
		for(int i = 0; i < 5; i++) {
			spa[i] = new SparseVector();
			int j = 0;
			while(! StdIn.isEmpty() && j < 5) {
				double val = StdIn.readDouble();
				spa[i].put(j++, val);
			}
		}
		for(int i = 0; i < 5; i++) {
			b[i] = spa[i].dot(x);
			System.out.println(b[i]);
		}
	}
}
