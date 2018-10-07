package Fundamentals.Practice;


public class Prac_1124 {
	public static int gcd(int a, int b) {
		System.out.println(a + "  " + b);
		if(a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		if(b == 0) {
			return a;
		}
		return gcd(b,(a % b));
	}
	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		System.out.println("the greatest common divisor of " + a + " and " + b + " is: " + gcd(a,b));
	}
}
