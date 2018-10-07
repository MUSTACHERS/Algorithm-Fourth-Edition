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
		Node x = h.right; // Ϊ�˽��������еĽ�С�ļ���Ϊ�����任Ϊ���ϴ�ļ���Ϊ�����
		h.right = x.left; // �� x��������ָ�� h��������
		x.left = h; // ��x ��Ϊ h �ĸ����
//		����x�Ǹ����
//		���ݺ�����Ķ��壺һ����㲻��ͬʱ����������������
		x.color = h.color; // �ɵã����������h.colorΪ��ɫ����
		h.color = RED; // ��ɺ�ɫ����
		x.N = h.N; // �ո�δ��תʱh.N������ �� ��ת�������ʱ��ͬ��
//		����ֻ�����h.N������
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
//	ר������ת��һ������������ɫ�ӽ�����ɫ
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
		root.color = BLACK; // ���������Ϊ��ɫ
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
		
		if(isRed(h.right) && ! isRed(h.left)) { // ����������������Ǻ�ɫ�������Ӳ��Ǻ�ɫ
			h = rotateLeft(h); // �� ����ת
		}
		if(isRed(h.left) && isRed(h.left.left)) { // ����������������Ǻ�ɫ���Ҹ������ӵ�������Ҳ�Ǻ�ɫ
			h = rotateRight(h); // �� ����ת
		}
		if(isRed(h.left) && isRed(h.right)) { // ��������������Ӻ������Ӷ��Ǻ�ɫ
			flipColors(h); // ��ı���ɫ
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
//	���ܣ���һ�����
//  ��Ϊ��deleteMin(h)�ж�h.left��h.left.left�����ж�
//	���h.left��h.left.left�����Ǻ�����(�ͱ�ʾ������2-���)
//	��ô��Ҫ����h.left�ĸ����h�����ֵܽ�㣨�������ҽ�㣩��һ�����
	private Node moveRedLeft(Node h) { 
//		�ȸı��������ɫ
		flipColors(h);
		if(isRed(h.right.left)) {  // �ж����ֵܽ��������Ƿ�Ϊ������
//			��ǰ���ĸ������ҽ���������ת
			h.right = rotateRight(h.right);
//			��ǰ���ĸ�����������ת
			h = rotateLeft(h);
//			�ٴθı���ɫ
			flipColors(h); // �㷨���İ���û���������
		}
		return h;
	}
	private Node deleteMin(Node h) {
//		�ݹ�Ľ���������һֱ������ݹ飬�������ս��
		if(h.left == null) {
			return null;
		}
//		�����ǰ�����������������������ɫ���Ǻ�ɫ(��ʾ2-���)
		if(! isRed(h.left) && ! isRed(h.left.left)) {
			h = moveRedLeft(h); // ����ҽ���һ�����
		}
//		��������ݹ�
		h.left = deleteMin(h.left);
//		ɾ���������޸���ɫ�ҽ��
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
//	ɾ��������ǰ���ǣ����ں�ɫ�����ӣ���ô�õ�������������Ϊ��ɫ��ֱ������ת��
//	��һ�ִ����������ֵܽڵ㲻��2-node���Ϳ���ֱ�Ӵ��ֵܽڵ��һ���ڵ����
//  �ڶ��ִ������ֵܽڵ���2-node����Ӹ��ڵ��н�һ��������Ȼ����ֵܽڵ�ϲ���һ��4-node
//	�ڶ��ַ�ʽֱ���޸���ɫ����
//	�������н�һ�����
	private Node moveRedRight(Node h) {
		flipColors(h);
//		�㷨���İ��� if(! isRed(h.left.left))
//		������ķ�ʽ�����һЩ
		if(isRed(h.left.left)) { // �����һ�ַ�ʽ
			h = rotateRight(h); // ����ת
			flipColors(h);
		}
		return h;
	}
	private Node deleteMax(Node h) {
//		ʹ�����ֺ�ɫ������
		if(isRed(h.left)) {
			h = rotateRight(h);
		}
//		�ݹ��������
		if(h.right == null) {
			return null;
		}
//		��֤h��h.rightΪ��ɫ��㣬�Ӹ��������
		if(! isRed(h.right) && ! isRed(h.right.left)) {
//			3-������ú�ɫ�����ģ��ģ���ɫ��㲻�������ҽ�㣬���Բ�������h.right.right��
//			��ͨ������һ������ת��ʹ��h.right.left���ָ�h.right.rightһ����Ч��
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
	
//	ɾ����㣬��Ҫɾ���Ľ����ײ�������Ȼ��ͱ��ɾ���ײ��Ľ�㣬
//	�Ϳ���ת����ɾ��������ɾ����С���
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
//			ɾ����С���еĲ���
			if(! isRed(root.left) && ! isRed(root.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		} else {
//			ɾ�������еĲ���
			if(isRed(h.left)) {
				h = rotateRight(h);
			}
			if(cmp == 0  && (h.right == null)) {
				return null;
			}
			if(! isRed(h.right) && ! isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if(cmp == 0) {  // �ҵ���ļ�������������С�Ľ�㽻����Ȼ��ɾ������������С�Ľ��
				h.val = get(h.right,min(h.right).key);
				h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			} else { // �Ҳ����ͼ������Ҳ���
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
			return -1; // �������ս��
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
