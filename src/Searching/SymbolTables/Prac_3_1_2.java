package Searching.SymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

/*
 * 一个基本的符号表需要包含以下方法：
 * void put(Key key, Value val);
 * Value get(Key key);
 * void delete(Key key);
 * boolean contains(Key key);
 * boolean isEmpty();
 * int size();
 * Iterator<Key> keys();A
 */
public class Prac_3_1_2 <Key, Value>{
	@SuppressWarnings("unused")
	private Key[] keys;
	@SuppressWarnings("unused")
	private Value[] vals;
	private static final int MAX = 8;
	private int N = 0;
	@SuppressWarnings("unchecked")
	public Prac_3_1_2() {
		this.keys = (Key[]) new Object[MAX];
		this.vals = (Value[]) new Object[MAX];
	}
	@SuppressWarnings("unchecked")
	public Prac_3_1_2(int capacity) {
		this.keys = (Key[]) new Object[capacity];
		this.vals = (Value[]) new Object[capacity];
	}
	public int size() {
		return this.N;
	}
	public boolean isEmpty() {
		return this.size() == 0;
	}
//	自动调整数组大小
	@SuppressWarnings("unchecked")
	public void resize(int max) {
		Key[] temp_a = (Key[]) new Object[max];
		Value[] temp_b = (Value[]) new Object[max];
		for(int i = 0; i < this.N; i++) {
			temp_a[i] = keys[i];
			temp_b[i] = vals[i];
		}
		keys = temp_a;
		vals = temp_b;
	}
	public Value get(Key key) {
		for(int i = 0; i < this.N; i++) {
			if(key.equals(keys[i])) {
				return vals[i];
			}
		}
		return null;
	}
	public void put(Key key, Value vals) {
		delete(key); // 避免重复
		if(this.N >= this.keys.length) {
			resize(2 * this.N); // 把数组长度扩两倍
		}
		this.keys[N] = key;
		this.vals[N] = vals;
		this.N++;
	}
	public void delete(Key key) {
		for(int i = 0; i < this.N; i++) {
			if(key.equals(keys[i])) {
//				因为是无序数组，所以用 N-1的位置的值赋值到 对应的位置
				this.keys[i] = this.keys[this.N - 1];
				this.vals[i] = this.vals[this.N - 1];
//				然后把N-1设置为null，让垃圾回收器回收
				this.keys[this.N - 1] = null;
				this.vals[this.N - 1] = null;
				this.N--;
//				判断是否要调整数组大小
				if(this.N > 0 && this.N == this.keys.length / 4) {
					resize(this.keys.length / 2);
				}
				return ;
			}
		}
	}
	public boolean contains(Key key) {
		for(int i = 0; i < this.N; i++) {
			if(key.equals(keys[i])) {
				return true;
			}
		}
		return false;
	}
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		for(int i = 0; i < this.N; i++) {
			q.enqueue(keys[i]);
		}
		return q;
	}
	
	public static void main(String[] args) {
		Prac_3_1_2<String, Integer> ch = new Prac_3_1_2<String, Integer>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			String[] Array = str.split("\\,");
			String key = Array[0];
			int val = Integer.parseInt(Array[1]);
			ch.put(key, val);
		}
		for(String s : ch.keys()) {
			System.out.println(s + " " + ch.get(s));
		}
	}
}
