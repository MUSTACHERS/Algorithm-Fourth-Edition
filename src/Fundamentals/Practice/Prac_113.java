package Fundamentals.Practice;


public class Prac_113 {
	public static void main(String[] args) {
		int one = Integer.parseInt(args[0]);
		int two = Integer.parseInt(args[1]);
		int three = Integer.parseInt(args[2]);
		if(one == two && one == three && two == three) {
			System.out.println("equal");
		} else {
			System.out.println("not equal");
		}
	}
}
