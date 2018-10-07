package Fundamentals.Practice;


public class Prac_1120 {
	public static double ln(int N) {
		if(Math.log(N) == 0) {
			return 0;
		}
		return (Math.log(N) + ln(N - 1));
	}
	public static void main(String[] args) {
		System.out.println(ln(2));
	}
}
