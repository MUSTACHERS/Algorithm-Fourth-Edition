package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Huffman {
	private static int R = 256; // ASCII��ĸ��
	private static class Node implements Comparable<Node> {
		// ���������ʲ������еĽ��
		private char ch; // �ڲ���㲻��ʹ�øñ���
		private int freq; // չ�����̲���ʹ�øñ���
		private final Node left, right;
		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		public boolean isLeaf() {
			return left == null && right == null;
		}
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}
	private static String[] buildCode(Node root) {
		// ʹ�õ��ʲ�������������
		String[] st = new String[R];
		buildCode(st, root, "");
		return st;
	}
	private static void buildCode(String[] st, Node x, String s) {
		// ʹ�õ��ʲ��������������ݹ飩
		if(x.isLeaf()) {
			st[x.ch] = s;
			return ;
		}
		buildCode(st, x.left, s + '0');
		buildCode(st, x.right, s + '1');
	}
	private static Node buildTrie(int[] freq) {
		// ʹ�ö�õ��������ʼ�����ȶ���
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char c = 0; c < R; c++) {
			if(freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		while(pq.size() > 1) {
			// �ϲ�����Ƶ����С����
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}
	private static void writeTrie(Node x) {
		// ������ʲ������ı����ַ���
		if(x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return ;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}
	private static Node readTrie() {
		if(BinaryStdIn.readBoolean()) {
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		}
		return new Node('\0', 0, readTrie(), readTrie());
	}
	public static void expand() {
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for(int i = 0; i < N; i++) {
			// չ����i����������Ӧ����ĸ
			Node x = root;
			while(! x.isLeaf()) { 
				if(BinaryStdIn.readBoolean()) { // ��ȡһ�����أ����Ϊ1���ƶ������ӽ��
					x = x.right;
				} else { // ���Ϊ0���ƶ������ӽ��
					x = x.left;
				}
			}
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}
	public static void compress() {
		// ��ȡ����
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		// ͳ��Ƶ��
		int[] freq = new int[R];
		for(int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}
		// ���������������
		Node root = buildTrie(freq);
		// (�ݹ��)��������
		String[] st = new String[R];
		buildCode(st, root, "");
		// (�ݹ��)��ӡ�����õĵ��ʲ�����
		writeTrie(root);
		// ��ӡ�ַ�����
		BinaryStdOut.write(input.length);
		// ʹ�û��������봦������
		for(int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for(int j = 0; j < code.length(); j++) {
				if(code.charAt(j) == '1') {
					BinaryStdOut.write(true);
				} else {
					BinaryStdOut.write(false);
				}
			}
		}
		BinaryStdOut.close();
	}
}
