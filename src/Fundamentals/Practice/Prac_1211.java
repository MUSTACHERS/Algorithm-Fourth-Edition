package Fundamentals.Practice;
class SmartDate {
	private int year;
	private int month;
	private int day;
	public SmartDate(int year, int month, int day) {
		if(!isDate(year,month,day)) {
			throw new IllegalArgumentException("Error Date: " + year + "/" + month + "/" + day	);
		}
		this.year = year;
		this.month = month;
		this.day = day;
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
	/**
	 * 
	 * @param a year
	 * @param b month
	 * @param c day
	 * @return is date?
	 */
	public boolean isDate(int a, int b, int c) {
//		��
		if(a <= 0 || a > 10000) {
			return false;
		}
//		��
		if(b < 1 || b > 12) {
			return false;
		}
//		��
		if(c < 1 || c > 31) {
			return false;
		}
//		12����ÿ���¸��ж�����(Ҫ���ǵ��Ƿ�Ϊ����)
		int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(c > months[b]) {
			return false;
		}
//		���������Ļ���������29��
		if(a % 400 == 0 || (a % 4 == 0 && a % 100 != 0)) {
			if(b == 2) {
				if(c > 29) {
					return false;
				}
			}
		}
		return true;
	}
}
public class Prac_1211 {
	public static void main(String[] args) {
		try {
			SmartDate a = new SmartDate(2000,3,25);
			System.out.println(a.getYear() + "/" + a.getMonth() + "/" + a.getDay());
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		try {
			SmartDate b = new SmartDate(2000,2,29); // 2000�����꣬��������29��
			System.out.println(b.getYear() + "/" + b.getMonth() + "/" + b.getDay());
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}
}
