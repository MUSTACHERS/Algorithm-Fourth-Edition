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
//		����xΪ�����������в��Ҳ�����key����Ӧ��ֵ
//		����Ҳ����򷵻�null
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key); // ���ҵļ�������ļ��Ƚ�
		if(cmp < 0) { // С�ڸ����ļ��Ļ������������в���
			return get(x.left, key);
		} else if(cmp > 0) { // ���ڸ����ļ��Ļ������������в���
			return get(x.right, key);
		} else { // �����ҵ��򷵻�key����Ӧ��ֵ
			return x.val;
		}
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val); // ���¸����
	}
	private Node put(Node x, Key key, Value val) {
//		���key��������xΪ���������������������ֵ
//		������key��valΪ��ֵ�Ե��½�����������
		if(x == null) { // �������Ϊ�գ����½�һ������
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0) { // ���keyС�ڸ���㣬�����������в���
			x.left = put(x.left, key, val); // ��ʱ����������
		} else if(cmp > 0) { // ���key���ڸ���㣬�����������в���
			x.right = put(x.right, key, val); // ��ʱ����������
		} else {
			x.val = val; // �ҵ������ֵ
		}
		x.N = size(x.left) + size(x.right) + 1; // ���½����N
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
//	floor(key): С�ڵ���key����������������ӽ���key��С�ڻ����key�ļ�
//	���������1.���keyС�ڸ����ļ����� С�ڵ���key������ һ�������Ӽ���
//	2.���key���ڸ����ļ����� ���ҽ����������д��� С�ڵ���key�Ľ�㣬��С�ڵ���key�������Ŵ����������У������������С�ڵ���key������
//	3.���key���ڸ����ļ����� ���ر���
	private Node floor(Node x, Key key) {  // <= key ������
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp == 0) { // ���key ���� ������key���򷵻ر���
			return x;
		}
		if(cmp < 0) { // ���key С�� ������key����ô <= key ������ һ�����������У������������в���
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);// ���key ���� ������key�������������в���
		// ��ô <= key ������ �п������������У����ҽ�������������һ���ڵ��keyֵҪС�ڵ��ڸ�key
		if(t != null) {
			return t; // ������������С�ڵ���key�������Ľ��
		} else { 
			return x; // ��������
		}
	}
	
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x == null) {
			return null;
		}
		return x.key;
	}
//	ceiling(key): ���ڵ���key����С������������ӽ���key�Ҵ��ڻ����key�ļ�
//	���������1.���key���ڸ����ļ����� ���ڵ���key����С�� һ����������������
//	2.���keyС�ڸ����ļ����� ���ҽ��� �������д��� ���ڵ��� key�Ľ�㣬���ڵ���key����С���Ŵ������������У�����������Ǵ��ڵ���key����С��
//	3.���key���ڸ����ļ����򷵻�����
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
//	������Ϊk�ļ�
	public Key selete(int k) {
		return selete(root, k).key;
	}
	private Node selete(Node x, int k) {
		if(x == null) {
			return null;
		}
		int t = size(x.left);  // �ȼ���������������Ľ����
		if(t > k) { // ���ӽ���� ���� ����k
			return selete(x.left, k);  // ��˵�� �ü������������в���
		} else if(t < k) { // ���ӽ�� С�� ����k
			return selete(x.right, k - t - 1); // ��˵�� ����k�ļ��ڸ�������������
//			��ʱ �����޸�Ϊ k - t - 1 �������� k - ���ӽ���� - �����
		} else { 
			return x; // �ҵ� �򷵻ؼ� 
		}
	} 
//	��������
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node x, Key key) {
		if(x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key); // key �� �����Ƚ�
		if(cmp < 0) { // key С�� �����
			return rank(x.left, key); // ��������������в���
		} else if(cmp > 0) { // key ���� �����
			return size(x.left) + 1 + rank(x.right, key); // ������������м������ң���ʱ�ð��������Ľ�����͸�����¼
		} else { // ���
			return size(x.left); // �������ӽ���� 
		}
	}
	
//	ɾ����С
	public void deleteMin() {
		root = deleteMin(root);
	}
	private Node deleteMin(Node x) {
		if(x.left == null) { // �����ӽ��Ϊ������
			return x.right; // ����������
		}
		x.left = deleteMin(x.left); // һֱ������������
		x.N = size(x.left) + size(x.right) + 1; // ���½����
		return x;
	}
//	ɾ�����
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
//	ɾ�����������������
//	1.����ҵ��Ľ�㣬������ �� ��������Ϊ������ �������� ��ֱ���øý��ĸ���� ָ�� ������
//	2.����ҵ��Ľ�㣬������ �� ������ �ǿ����� �������� ���������Ϊ�գ����øý��ĸ����ָ�� �����������������Ϊ�գ����øý��ĸ����ָ�� ������
//	3.����ҵ��Ľ�㣬������ �� ������ �� ��Ϊ�� �������� �����ø����ĸ�����Ϊ�˱������������������������������Ѱ����С��Ȼ�󸳸�����㣬
//					Ȼ��Ѹ����������������¸����������������������г���С�������¸�����������
	public void delete(Key key) {
		root = delete(root, key);
	}
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key); // �� key �ڸ������бȽ�
		if(cmp < 0) {
			x.left = delete(x.left, key); // �����������в���
		} else if(cmp > 0) {
			x.right = delete(x.right, key); // �����������в���
		} else { // �ҵ�
//			�ɴ����1��2�����
			if(x.right == null) { 
				return x.left;
			}
			if(x.left == null) {
				return x.right;
			}
//			�����3�����
			Node t = x; // �ȴ��������ĸ���
			x = min(t.right); // �� ������������ ����С�ļ����������
			x.left = t.left; // �� ������������ ���� ������������
			x.right = deleteMin(t.right); // �� �������������г� ���������е���С�� ���� ������������
		}
		x.N = size(x.left) + size(x.right) + 1; // ���½����
		return x;
	}
	
//	�������
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
		int cmplo = lo.compareTo(x.key);  // lo �� �����Ƚ�
		int cmphi = hi.compareTo(x.key); // hi �� �����Ƚ�
		if(cmplo < 0) { // x.key�ڴ���lo�����Χ�ڡ�
			keys(x.left, queue, lo, hi); // ����һ������ƶ�
		}
		if(cmplo <= 0 && cmphi >= 0) { // ���������� [lo,hi] ��Χ��
			queue.enqueue(x.key); // �� �������ļ�����
		}
		if(cmphi > 0) {  // x.key��С��hi�����Χ��
		}
		keys(x.right, queue, lo, hi); // ����һ������ƶ�
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public int height() {
		return height(root);
	}
	private int height(Node x) {
		if(x == null) {
			return 0; // ����������
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
