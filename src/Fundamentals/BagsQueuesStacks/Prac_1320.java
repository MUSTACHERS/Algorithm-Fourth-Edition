package Fundamentals.BagsQueuesStacks;

/**
 * 
 * @author MusTache
 */
public class Prac_1320 {
	
	public static void main(String[] args) {
		MyLink a = new MyLink();
		a.add("a");
		a.add("b");
		a.add("c");
		
		a.print();
		System.out.println(a.size());
		a.delete(2);
		a.print();
		System.out.println(a.size());
	}
}
