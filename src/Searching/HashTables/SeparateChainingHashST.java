package Searching.HashTables;



import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST <Key, Value>{
	private int N;  // 键值对的总数
	private int M;  // 散列表的大小
	private SequentialSearchST<Key, Value>[] st;  // 存放链表对象的数组
	
	public SeparateChainingHashST() {
		this(997);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key,Value>[]) new SequentialSearchST[M]; // 泛型数组需要强制转换
		for(int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public Value get(Key key) {
		return (Value) st[hash(key)].get(key);
	}
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}
	public void delete(Key key) {
		int i = hash(key);
		if(st[i].contains(key)) {
			N--;
		}
		st[i].delete(key);
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++) {
			for(Key key : st[i].keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}
}
