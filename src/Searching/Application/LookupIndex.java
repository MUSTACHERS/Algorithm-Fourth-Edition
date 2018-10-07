package Searching.Application;

import Searching.BalanceSearchTree.RedBlackTree;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author MUSTACHE
 * 
 * %java LookupIndex aminoI.txt ","
 * Serine
 *  TCT
 *  TCA
 *  TCG
 *  AGT
 *  AGC
 * TCG
 *  Serine
 *  
 * %java LookupIndex movies.txt "/"
 * Bacon, Kevin
 *  Animal House (1978)
 *  Apollo 13 (1995)
 * 
 * Tin Men (1987)
 *  DeBoy, David
 *  Blumenfeld, Alan
 */
public class LookupIndex {
	public static void main(String[] args) {
		In in = new In(args[0]); // �������ݿ�
		String sp = args[1]; // �ָ���
//		st ���洢 key 
//		ts ���洢 val
		RedBlackTree<String, Queue<String>> st = new RedBlackTree<String, Queue<String>>();
		RedBlackTree<String, Queue<String>> ts = new RedBlackTree<String, Queue<String>>();
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			String key = a[0];
			for(int i = 1; i < a.length; i++) {
				String val = a[i];
				if(! st.contains(key)) {  // st ��ż�
					st.put(key, new Queue<String>());
				}
				if(! ts.contains(val)) { // ts ���ֵ
					ts.put(val, new Queue<String>());
				}
				st.get(key).enqueue(val); // ������Ѿ����ڣ����val���뵽st�Ķ�����
				ts.get(val).enqueue(key); // ���ֵ�Ѿ����ڣ����key���뵽st�Ķ�����
			}
		}
		while(! StdIn.isEmpty()) {
			String query = StdIn.readLine(); // �������ж�ȡ
			if(st.contains(query)) { // ���query������st��
				for(String s : st.get(query)) { //  ���ӡ��query��ص�����ֵ 
					StdOut.println(" " + s);
				}
			}
			if(ts.contains(query)) {
				for(String s : ts.get(query)) {
					StdOut.println(" " + s);//  ���ӡ��query��ص����м�
				}
			}
		}
	}
}
