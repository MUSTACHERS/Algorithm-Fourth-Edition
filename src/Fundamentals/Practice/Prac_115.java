package Fundamentals.Practice;

import edu.princeton.cs.algs4.StdIn;

public class Prac_115 {
	public static void main(String[] args) {
		double x = StdIn.readDouble(); // ��ȡһ��double���͵�ֵ
		double y = StdIn.readDouble();
		if((x >= 0 && x <= 1) && (y >= 0 && y <= 1)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
