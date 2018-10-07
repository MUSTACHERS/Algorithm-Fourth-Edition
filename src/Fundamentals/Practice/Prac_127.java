package Fundamentals.Practice;

public class Prac_127 {
	public static String mystery(String s) {
		int N = s.length();
		if(N <= 1) {
			return s;
		}
		String a = s.substring(0, N/2);
		String b = s.substring(N/2, N);
		return mystery(b) + mystery(a);
	}
	public static void main(String[] args) {
		System.out.println(mystery("ab"));
		System.out.println(mystery("hello"));
	}
}
