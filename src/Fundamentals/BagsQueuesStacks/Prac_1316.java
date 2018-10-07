package Fundamentals.BagsQueuesStacks;

import java.util.Scanner;

class Date {
	private int year;
	private int month;
	private int day;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public Date(String date) {
		String[] temp = date.split("\\/");
		if(temp.length != 3) {
			throw new IllegalArgumentException("Argument illegal: " + date);
		}
		this.day = Integer.parseInt(temp[0]);
		this.month = Integer.parseInt(temp[1]);
		this.year = Integer.parseInt(temp[2]);
	}
	public int getYear() {
		return this.year;
	}
	public int getMonth() {
		return this.month;
	}
	public int getDay() {
		return this.day;
	}
	public String toString() {
		return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
	}
	public static Date[] readDates(String s) {
		Queue<Date> queue = new Queue<Date>();
		String[] temp = s.split(" ");
		int n = temp.length;
		for(int i  = 0; i < n; i++) {
			queue.enqueue(new Date(temp[i]));
		}
		
		Date[] result = new Date[n];
		for(int i = 0; i < n; i++) {
			result[i] = queue.dequeue();
		}
		return result;
	}
	
}
public class Prac_1316 {
	
	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		String t = null;
		while(sca.hasNext()) {
			t = sca.nextLine();
		}
		Date[] date = Date.readDates(t);
		for(int i = 0; i < date.length; i++) {
			System.out.println(date[i]);
		}
	}
}
