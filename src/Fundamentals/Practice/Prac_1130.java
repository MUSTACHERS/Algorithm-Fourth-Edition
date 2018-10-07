package Fundamentals.Practice;

public class Prac_1130 {
	public static boolean isPrime(int a, int b) {
		if(a > b) {
			a = a ^ b;
			b = a ^ b;
			a = a ^ b;
		}
		for(int i = 2; i <= a; i++) {
			if(a % b == 0 && b % a == 0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		boolean[][] a = new boolean[3][4];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j ++) {
				a[i][j] = isPrime(i,j);
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
