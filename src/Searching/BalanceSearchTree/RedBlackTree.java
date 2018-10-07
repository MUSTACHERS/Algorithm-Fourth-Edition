package Searching.BalanceSearchTree;

import edu.princeton.cs.algs4.Queue;

public class RedBlackTree <Key extends Comparable<Key>, Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	private class Node {
		private Key key;
		private Value val;
		private Node left,right;
		private int N;
		private boolean color;
		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	private boolean isRed(Node x) {
		if(x == null) {
			return false;
		}
		return x.color == RED;
	}	
	private Node rotateLeft(Node h) {
		Node x = h.right; // 为了将两个键中的较小的键作为根结点变换为将较大的键作为根结点
		h.right = x.left; // 让 x的左子树指向 h的右子树
		x.left = h; // 让x 作为 h 的根结点
//		现在x是根结点
//		根据红黑树的定义：一个结点不会同时和两条红链接相连
		x.color = h.color; // 可得，正常情况下h.color为黑色链接
		h.color = RED; // 变成红色链接
		x.N = h.N; // 刚刚未旋转时h.N的数量 跟 旋转后的数量时相同的
//		现在只需更新h.N的数量
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
//	专门用于转换一个结点的两个红色子结点的颜色
	private void flipColors(Node h) {
//		h.color = RED; 
		h.color = !h.color;
//		h.left.color = BLACK;
		h.left.color = ! h.left.color;
//		h.right.color = BLACK;
		h.right.color = ! h.right.color;
	}
	private int size(Node h) {
		if(h == null) {
			return 0;
		}
		return h.N;
	}
	public int size() {
		return size(root);
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK; // 根结点设置为黑色
	}
	private Node put(Node h, Key key, Value val) {
		if(h == null) {
			return new Node(key, val, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		if(cmp > 0) {
			h.right = put(h.right, key, val);
		} else if(cmp < 0) {
			h.left = put(h.left, key, val);
		} else {
			h.val = val;
		}
		
		if(isRed(h.right) && ! isRed(h.left)) { // 如果根结点的右链接是红色且左链接不是红色
			h = rotateLeft(h); // 则 左旋转
		}
		if(isRed(h.left) && isRed(h.left.left)) { // 如果根结点的左链接是红色并且该左链接的做链接也是红色
			h = rotateRight(h); // 则 右旋转
		}
		if(isRed(h.left) && isRed(h.right)) { // 如果根结点的左链接和右链接都是红色
			flipColors(h); // 则改变颜色
		}
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node h, Key key) {
		if(h == null) {
			return null;
		}
		int cmp = key.compareTo(h.key);
		if(cmp < 0) {
			return get(h.left, key);
		} else if(cmp > 0) {
			return get(h.right, key);
		} else {
			return h.val;
		}
	}
	
	public Key min() {
		return min(root).key;
	}
	private Node min(Node h) {
		if(h.left == null) {
			return h;
		}
		return min(h.left);
	}
	public Key max() {
		return max(root).key;
	}
	private Node max(Node h) {
		if(h.right == null) {
			return h;
		}
		return max(h.right);
	}
	
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node h, Key key) {
		if(h == null) {
			return 0;
		}
		int cmp = key.compareTo(h.key);
		if(cmp < 0) {
			return rank(h.left, key);
		} else if(cmp > 0) {
			return rank(h.right, key) + 1 + size(h.left);
		} else {
			return size(h.left);
		}
	}
	
	public Key selete(int k) {
		return selete(root, k).key;
	}
	private Node selete(Node h, int k) {
		if(h == null) {
			return null;
		}
		int t = size(h.left);
		if(t > k) {
			return selete(h.left, k);
		} else if(t < k) {
			return selete(h.right, k - t - 1);
		} else {
			return h;
		}
	}
	
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}
	private Node floor(Node h, Key key) {
		if(h == null) {
			return null;
		}
		int cmp = key.compareTo(h.key);
		if(cmp == 0) {
			return h;
		}
		if(cmp < 0) {
			return floor(h.left, key);
		}
		Node t = floor(h.right, key);
		if(t != null) {
			return t;
		} else {
			return h;
		}
	}
	
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}
	private Node ceiling(Node h, Key key) {
		if(h == null) {
			return null;
		}
		int cmp = key.compareTo(h.key);
		if(cmp == 0) {
			return h;
		}
		if(cmp > 0) {
			return ceiling(h.right, key);
		}
		Node t = ceiling(h.left, key);
		if(t != null) {
			return t;
		} else {
			return h;
		}
	}
	public boolean isEmpty() {
		return root.N == 0;
	}
	private Node balance(Node h) {
		if(isRed(h.right)) {
			h = rotateLeft(h);  
		}
		if(isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if(isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.N = size(h.left) + 1 + size(h.right);
		return h;
	}
//	功能：借一个结点
//  因为在deleteMin(h)中对h.left和h.left.left进行判断
//	如果h.left和h.left.left都不是红链接(就表示是两个2-结点)
//	那么就要传入h.left的父结点h，从兄弟结点（父结点的右结点）借一个结点
	private Node moveRedLeft(Node h) { 
//		先改变根结点的颜色
		flipColors(h);
		if(isRed(h.right.left)) {  // 判断其兄弟结点的左结点是否为红链接
//			当前结点的父结点的右结点进行右旋转
			h.right = rotateRight(h.right);
//			当前结点的父结点进行左旋转
			h = rotateLeft(h);
//			再次改变颜色
			flipColors(h); // 算法第四版中没有这个步骤
		}
		return h;
	}
	private Node deleteMin(Node h) {
//		递归的结束条件：一直往左结点递归，则到遇到空结点
		if(h.left == null) {
			return null;
		}
//		如果当前结点的左结点和其左结点的左结点的颜色不是红色(表示2-结点)
		if(! isRed(h.left) && ! isRed(h.left.left)) {
			h = moveRedLeft(h); // 则从右结点借一个结点
		}
//		继续往左递归
		h.left = deleteMin(h.left);
//		删除后，用于修复红色右结点
		return balance(h);
	}
	public void deleteMin() {
		if(! isRed(root.left) && ! isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMin(root);
		if(! isEmpty()) {
			root.color = BLACK;
		}
	}
//	
//	删除最大键的前提是：存在红色右链接（怎么得到：利用左链接为红色，直接右旋转）
//	第一种处理方法就是兄弟节点不是2-node，就可以直接从兄弟节点借一个节点过来
//  第二种处理方法兄弟节点是2-node，则从父节点中借一个过来，然后和兄弟节点合并成一个4-node
//	第二种方式直接修改颜色即可
//	从左结点中借一个结点
	private Node moveRedRight(Node h) {
		flipColors(h);
//		算法第四版是 if(! isRed(h.left.left))
//		用下面的方式好理解一些
		if(isRed(h.left.left)) { // 处理第一种方式
			h = rotateRight(h); // 右旋转
			flipColors(h);
		}
		return h;
	}
	private Node deleteMax(Node h) {
//		使树出现红色右链接
		if(isRed(h.left)) {
			h = rotateRight(h);
		}
//		递归结束条件
		if(h.right == null) {
			return null;
		}
//		保证h或h.right为红色结点，从父结点入手
		if(! isRed(h.right) && ! isRed(h.right.left)) {
//			3-结点是用红色结点来模拟的，红色结点不可能是右结点，所以不可能是h.right.right。
//			但通过上面一次右旋转，使得h.right.left出现跟h.right.right一样的效果
			h = moveRedRight(h);
		}
		h.right = deleteMax(h.right);
		return balance(h);
	}
	public void deleteMax() {
		if(! isRed(root.left) && ! isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMax(root);
		if(! isEmpty()) {
			root.color = BLACK;
		}
	}
	
//	删除结点，把要删除的结点与底部交换，然后就变成删除底部的结点，
//	就可以转换成删除最大结点或删除最小结点
	public void delete(Key key) {
		if(! isRed(root.left) && ! isRed(root.right)) {
			root.color = RED;
		}
		root = delete(root, key);
		if(! isEmpty()) {
			root.color = BLACK;
		}
	}
	private Node delete(Node h, Key key) {
		int cmp = key.compareTo(h.key);
		if(cmp < 0) {
//			删除最小键中的步骤
			if(! isRed(root.left) && ! isRed(root.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		} else {
//			删除最大键中的步骤
			if(isRed(h.left)) {
				h = rotateRight(h);
			}
			if(cmp == 0  && (h.right == null)) {
				return null;
			}
			if(! isRed(h.right) && ! isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if(cmp == 0) {  // 找到后的键与右子树中最小的结点交换，然后删除右子树中最小的结点
				h.val = get(h.right,min(h.right).key);
				h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			} else { // 找不到就继续往右查找
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
	}
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public int height() {
		return height(root);
	}
	private int height(Node x) {
		if(x == null) {
			return -1; // 不包含空结点
		}
		return 1 + Math.max(height(x.left), height(x.right));
	}
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
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) {
			keys(x.left, queue, lo, hi);
		}
		if(cmplo <= 0 && cmphi >= 0) {
			queue.enqueue(x.key);
		}
		if(cmphi > 0) {
			keys(x.right, queue, lo, hi);
		}
	}
}
