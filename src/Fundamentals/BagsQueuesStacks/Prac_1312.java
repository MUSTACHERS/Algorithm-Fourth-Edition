package Fundamentals.BagsQueuesStacks;

public class Prac_1312 {
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("first");
		stack.push("second");
		stack.push("third");
		
		
		Stack<String> str = Stack.copy(stack);
		while(!str.isEmpty()) {
			System.out.println(str.pop());
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
