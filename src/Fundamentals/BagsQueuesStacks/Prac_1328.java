package Fundamentals.BagsQueuesStacks;

public class Prac_1328 {
	public static void main(String[] args) {
		Link link = new Link();
		link.add(1);
		link.add(2);
		link.add(5);
		link.add(20);
		link.add(8);
		link.add(9);
		link.add(3);
		link.add(4);
		System.out.println(link.maxResursive());
	}
}
