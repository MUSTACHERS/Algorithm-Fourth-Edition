package Fundamentals.BagsQueuesStacks;

public class Prac_1326 {
	
	public static void main(String[] args) {
		MyLink link = new MyLink();
		link.add("a");
		link.add("b");
		link.add("b");
		link.add("a");
		link.remove("a");
		link.print();
	}
}
