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
 * Ϊһ���ļ�������������ÿ���ļ��е�ÿ�����ʶ���¼�ڷ��ű��в�ά��һ��SET������������ֹ��ĸõ��ʵ��ļ�
 * 
 * �г��Թ������·�����У����в���
 * 
 * % java FileIndex ex*.txt (PS:������Ҫ�ļ��ľ���·��)
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
			while(! in.isEmpty()) { // ������ű�
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
					StdOut.println(" " + file.getName()); // ��ӡ���ļ���
				}
			}
		}
	}
}
