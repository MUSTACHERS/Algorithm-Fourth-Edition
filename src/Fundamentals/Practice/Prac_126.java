package Fundamentals.Practice;

public class Prac_126 {
	public static void main(String[] args) {
		String s = args[0];
		String t = args[1];
		if(s.length() == t.length() && s.concat(s).indexOf(t) != -1) { // 第二个条件有点不懂——s.concat(s)——为什么还要连接s
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
