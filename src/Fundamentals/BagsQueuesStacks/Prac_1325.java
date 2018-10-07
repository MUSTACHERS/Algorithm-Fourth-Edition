package Fundamentals.BagsQueuesStacks;

public class Prac_1325 {

	public static void main(String[] args) {
		MyLink link = new MyLink();
		link.add("a");
		link.add("b");
		link.add("c");
		link.add("d");
		link.print();
//		要把Node类设置为static
		MyLink.Node node1 = link.search("b");
		MyLink.Node node2 = new MyLink.Node("F");

		link.insertAfter(node1, node2);
		link.print();
	}
}
