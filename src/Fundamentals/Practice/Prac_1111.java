package Fundamentals.Practice;


public class Prac_1111 {
	public static void main(String[] args) {
		boolean[][] array = {{true,false,true},{false,true,false},{false,true,false}};
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print("\t"  + (j + 1) + "\t");
			}
			break;
		}
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			System.out.print((i + 1));
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] == true) {
					System.out.print("\t" + "*" + "\t");
				} else {
					System.out.print("\t" + " " + "\t");
				}
			}
			System.out.println();
		}
	}
}
