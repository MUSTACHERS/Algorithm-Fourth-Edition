package Fundamentals.Practice;

public class Prac_126 {
	public static void main(String[] args) {
		String s = args[0];
		String t = args[1];
		if(s.length() == t.length() && s.concat(s).indexOf(t) != -1) { // �ڶ��������е㲻������s.concat(s)����Ϊʲô��Ҫ����s
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
