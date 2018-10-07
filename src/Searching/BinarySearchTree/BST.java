package Searching.BinarySearchTree;

import edu.princeton.cs.algs4.Queue;

public class BST <Key extends Comparable<Key>, Value>{
	private Node root;
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N) { 
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if(x == null) {
			return 0;
		} else {
			return x.N;
		}
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node x, Key key) {
//		在以x为根结点的子树中查找并返回key所对应的值
//		如果找不到则返回null
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key); // 查找的键与根结点的键比较
		if(cmp < 0) { // 小于根结点的键的话则在左子树中查找
			return get(x.left, key);
		} else if(cmp > 0) { // 大于根结点的键的话则在右子树中查找
			return get(x.right, key);
		} else { // 否则找到则返回key所对应的值
			return x.val;
		}
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val); // 更新根结点
	}
	private Node put(Node x, Key key, Value val) {
//		如果key存在于以x为根结点的子树中则更新它的值
//		否则将以key和val为键值对的新结点插入子树中
		if(x == null) { // 如果子树为空，则新建一个子树
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) { // 如果key小于根结点，则在左子树中查找
			x.left = put(x.left, key, val); // 随时更新左子树
		} else if(cmp > 0) { // 如果key大于根结点，则在右子树中查找
			x.right = put(x.right, key, val); // 随时更新右子树
		} else {
			x.val = val; // 找到则更新值
		}
		x.N = size(x.left) + size(x.right) + 1; // 更新结点数N
		return x;
	}
	
	public Key min() {
		return min(root).key;
	}
	private Node min(Node x) {
		if(x.left == null) {
			return x;
		}
		return min(x.left);
	}
	
	public Key max() {
		return max(root).key;
	}
	private Node max(Node x) {
		if(x.right == null) {
			return x;
		}
		return max(x.right);
	}
	
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}
//	floor(key): 小于等于key的最大键————最接近于key且小于或等于key的键
//	三种情况：1.如果key小于根结点的键，则 小于等于key的最大键 一定在左子键中
//	2.如果key大于根结点的键，则 当且仅当右子树中存在 小于等于key的结点，则小于等于key的最大键才存在右子树中，否则根结点就是小于等于key的最大键
//	3.如果key等于根结点的键，则 返回本身
	private Node floor(Node x, Key key) {  // <= key 的最大键
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) { // 如果key 等于 根结点的key，则返回本身
			return x;
		}
		if(cmp < 0) { // 如果key 小于 根结点的key，那么 <= key 的最大键 一定在左子树中，则在左子树中查找
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);// 如果key 大于 根结点的key，则在右子树中查找
		// 那么 <= key 的最大键 有可能在右子树中，当且仅当其右子树有一个节点的key值要小于等于该key
		if(t != null) {
			return t; // 返回右子树中小于等于key的最大键的结点
		} else { 
			return x; // 返回自身
		}
	}
	
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}
//	ceiling(key): 大于等于key的最小键————最接近于key且大于或等于key的键
//	三种情况：1.如果key大于根结点的键，则 大于等于key的最小键 一定存在于右子树中
//	2.如果key小于根结点的键，则 当且仅当 左子树中存在 大于等于 key的结点，大于等于key的最小键才存在于左子树中，否则根结点就是大于等于key的最小键
//	3.如果key等于根结点的键，则返回自身
	private Node ceiling(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) {
			return x;
		}
		if(cmp > 0) {
			return ceiling(x.right, key);
		}
		Node t = ceiling(x.left, key);
		if(t != null) {
			return t;
		} else {
			return x;
		}
	}
//	找排名为k的键
	public Key selete(int k) {
		return selete(root, k).key;
	}
	private Node selete(Node x, int k) {
		if(x == null) {
			return null;
		}
		int t = size(x.left);  // 先计算根结点的左子树的结点数
		if(t > k) { // 左子结点数 大于 排名k
			return selete(x.left, k);  // 则说明 得继续在左子树中查找
		} else if(t < k) { // 左子结点 小于 排名k
			return selete(x.right, k - t - 1); // 则说明 排名k的键在根结点的右子树中
//			此时 排名修改为 k - t - 1 ———— k - 左子结点数 - 根结点
		} else { 
			return x; // 找到 则返回键 
		}
	} 
//	键的排名
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node x, Key key) {
		if(x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key); // key 与 根结点比较
		if(cmp < 0) { // key 小于 根结点
			return rank(x.left, key); // 则继续在左子树中查找
		} else if(cmp > 0) { // key 大于 根结点
			return size(x.left) + 1 + rank(x.right, key); // 则得在右子树中继续查找，此时得把左子树的结点数和根结点记录
		} else { // 相等
			return size(x.left); // 返回左子结点数 
		}
	}
	
//	删除最小
	public void deleteMin() {
		root = deleteMin(root);
	}
	private Node deleteMin(Node x) {
		if(x.left == null) { // 则到左子结点为空连接
			return x.right; // 返回右子树
		}
		x.left = deleteMin(x.left); // 一直往左子树查找
		x.N = size(x.left) + size(x.right) + 1; // 更新结点数
		return x;
	}
//	删除最大
	public void deleteMax() {
		root = deleteMax(root);
	}
	private Node deleteMax(Node x) {
		if(x.right == null) {
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
//	删除操作分三种情况：
//	1.如果找到的结点，左子树 和 右子树都为空子树 ———— 则直接让该结点的父结点 指向 空链接
//	2.如果找到的结点，左子树 或 右子树 是空子树 ———— 如果左子树为空，则让该结点的父结点指向 右子树，如果右子树为空，则让该结点的父结点指向 左子树
//	3.如果找到的结点，左子树 和 右子树 都 不为空 ———— 创建该根结点的副本（为了保存根结点的左右子树），在右子树中寻找最小键然后赋给根结点，
//					然后把副本的左子树赋给新根结点的左子树，把右子树中除最小键赋给新根结点的右子树
	public void delete(Key key) {
		root = delete(root, key);
	}
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key); // 对 key 于根结点进行比较
		if(cmp < 0) {
			x.left = delete(x.left, key); // 则在左子树中查找
		} else if(cmp > 0) {
			x.right = delete(x.right, key); // 则在右子树中查找
		} else { // 找到
//			可处理第1，2种情况
			if(x.right == null) { 
				return x.left;
			}
			if(x.left == null) {
				return x.right;
			}
//			处理第3种情况
			Node t = x; // 先创建根结点的副本
			x = min(t.right); // 把 副本的右子树 中最小的键赋给根结点
			x.left = t.left; // 把 副本的左子树 赋给 根结点的左子树
			x.right = deleteMin(t.right); // 把 副本的右子树中除 在右子树中的最小键 赋给 根结点的右子树
		}
		x.N = size(x.left) + size(x.right) + 1; // 更新结点数
		return x;
	}
	
//	中序遍历
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	private Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if(x == null) {
			return ;
		}
		int cmplo = lo.compareTo(x.key);  // lo 与 根结点比较
		int cmphi = hi.compareTo(x.key); // hi 与 根结点比较
		if(cmplo < 0) { // x.key在大于lo这个范围内。
			keys(x.left, queue, lo, hi); // 向下一个结点移动
		}
		if(cmplo <= 0 && cmphi >= 0) { // 如果根结点在 [lo,hi] 范围内
			queue.enqueue(x.key); // 则 将根结点的键入列
		}
		if(cmphi > 0) {  // x.key在小于hi这个范围内
		}
		keys(x.right, queue, lo, hi); // 向下一个结点移动
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public int height() {
		return height(root);
	}
	private int height(Node x) {
		if(x == null) {
			return 0; // 包含空链接
		}
		return Math.max(height(x.left),height(x.right)) + 1;
	}
	
	@SuppressWarnings("unused")
	private int size(Key lo, Key hi) {
		if(lo.compareTo(hi) > 0) {
			return 0;
		}
		if(contains(hi)) {
			return rank(hi) - rank(lo) + 1;
		} else {
			return rank(hi) - rank(lo);
		}
	}
	
	
}
