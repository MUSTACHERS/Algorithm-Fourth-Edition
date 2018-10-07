package Searching.Application;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author MUSTACHE
 * 为一组文件创建索引，将每个文件中的每个单词都记录在符号表中并维护一个SET对象来保存出现过的该单词的文件
 * 
 * 有尝试过用相对路径运行，运行不了
 * 
 * % java FileIndex ex*.txt (PS:这里需要文件的绝对路径)
 * age
 *  ex3.txt 
 *  ex4.txt
 * best
 *  ex1.txt
 * was
 *  ex1.txt
 *  ex2.txt
 *  ex3.txt
 *  ex4.txt
 */
public class FileIndex {
	public static void main(String[] args) {
		ST<String, SET<File>> st = new ST<String, SET<File>>();
		for(String filename : args) {
			File file = new File(filename);
			In in = new In(file);
			while(! in.isEmpty()) { // 存入符号表
				String word = in.readString();
				if(! st.contains(word)) {
					st.put(word, new SET<File>());
				}
				SET<File> set =  st.get(word);
				set.add(file);
			}
		}
		while(! StdIn.isEmpty()) {
			String query = StdIn.readString();
			if(st.contains(query)) {
				for(File file : st.get(query)) {
					StdOut.println(" " + file.getName()); // 打印出文件名
				}
			}
		}
	}
}
