package String.DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Huffman {
	private static int R = 256; // ASCII字母表
	private static class Node implements Comparable<Node> {
		// 霍夫曼单词查找树中的结点
		private char ch; // 内部结点不会使用该变量
		private int freq; // 展开过程不会使用该变量
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
		// 使用单词查找树构造编译表
		String[] st = new String[R];
		buildCode(st, root, "");
		return st;
	}
	private static void buildCode(String[] st, Node x, String s) {
		// 使用单词查找树构造编译表（递归）
		if(x.isLeaf()) {
			st[x.ch] = s;
			return ;
		}
		buildCode(st, x.left, s + '0');
		buildCode(st, x.right, s + '1');
	}
	private static Node buildTrie(int[] freq) {
		// 使用多棵单结点树初始化优先队列
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char c = 0; c < R; c++) {
			if(freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		while(pq.size() > 1) {
			// 合并两棵频率最小的树
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}
	private static void writeTrie(Node x) {
		// 输出单词查找树的比特字符串
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
			// 展开第i个编码所对应的字母
			Node x = root;
			while(! x.isLeaf()) { 
				if(BinaryStdIn.readBoolean()) { // 读取一个比特，如果为1则移动到右子结点
					x = x.right;
				} else { // 如果为0则移动到左子结点
					x = x.left;
				}
			}
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}
	public static void compress() {
		// 读取输入
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		// 统计频率
		int[] freq = new int[R];
		for(int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}
		// 构造霍夫曼编码树
		Node root = buildTrie(freq);
		// (递归地)构造编译表
		String[] st = new String[R];
		buildCode(st, root, "");
		// (递归地)打印编码用的单词查找树
		writeTrie(root);
		// 打印字符总数
		BinaryStdOut.write(input.length);
		// 使用霍夫曼编码处理输入
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
