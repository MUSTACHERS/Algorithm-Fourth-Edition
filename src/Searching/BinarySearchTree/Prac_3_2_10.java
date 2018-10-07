package Searching.BinarySearchTree;

public class Prac_3_2_10 {
	public static void main(String[] args) {
		BST<String, Integer> sbt = new BST<String, Integer>();
		sbt.put("A", 1);
		sbt.put("B", 2);
		sbt.put("D", 3);
		sbt.put("C", 4);
		sbt.put("F", 5);
		for(String s : sbt.keys()) {
			System.out.println(s + " " + sbt.get(s));
		}
//		TEXT
		System.out.println(sbt.min());
		System.out.println(sbt.max());
		System.out.println(sbt.floor("E"));
		System.out.println(sbt.ceiling("F"));
		System.out.println(sbt.selete(2));
		System.out.println(sbt.rank("C"));
		sbt.delete("F");
		sbt.deleteMin();
		sbt.deleteMax();
		for(String s : sbt.keys()) {
			System.out.println(s + " " + sbt.get(s));
		}
	}
}
