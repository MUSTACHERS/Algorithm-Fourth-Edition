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
		In in = new In(args[0]); // 索引数据库
		String sp = args[1]; // 分隔符
//		st 来存储 key 
//		ts 来存储 val
		RedBlackTree<String, Queue<String>> st = new RedBlackTree<String, Queue<String>>();
		RedBlackTree<String, Queue<String>> ts = new RedBlackTree<String, Queue<String>>();
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			String key = a[0];
			for(int i = 1; i < a.length; i++) {
				String val = a[i];
				if(! st.contains(key)) {  // st 存放键
					st.put(key, new Queue<String>());
				}
				if(! ts.contains(val)) { // ts 存放值
					ts.put(val, new Queue<String>());
				}
				st.get(key).enqueue(val); // 如果键已经存在，则把val存入到st的队列中
				ts.get(val).enqueue(key); // 如果值已经存在，则把key存入到st的队列中
			}
		}
		while(! StdIn.isEmpty()) {
			String query = StdIn.readLine(); // 从输入中读取
			if(st.contains(query)) { // 如果query存在于st中
				for(String s : st.get(query)) { //  则打印与query相关的所有值 
					StdOut.println(" " + s);
				}
			}
			if(ts.contains(query)) {
				for(String s : ts.get(query)) {
					StdOut.println(" " + s);//  则打印与query相关的所有键
				}
			}
		}
	}
}
