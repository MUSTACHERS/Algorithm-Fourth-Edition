package Fundamentals.Practice;


public class Prac_1113 {
	public static void main(String[] args) {
		int[][] arr = {{2,2,3},{4,4,5}};
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		reverse(arr);
	}
	public static void reverse(int[][] arr) {
		int[][] a = new int[arr[0].length][arr.length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				a[j][i] = arr[i][j];
			}
		}
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
