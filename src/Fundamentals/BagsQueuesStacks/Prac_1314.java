package Fundamentals.BagsQueuesStacks;

public class Prac_1314 {
	
	private String[] a;
	private int head;
	private int end;
	private int N;
	
	public Prac_1314() {
		a = new String[1];
		head = 0;
		end = 0;
		N = 0;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public boolean isFull() {
		return N == a.length;
	}
	public void resize(int max) {
		String[] temp = new String[max];
		for(int i =0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	public void enqueue(String item) {
		if(isFull()) {
			resize(2 * a.length);
		}
		a[end++] = item;
		N++;
	}
	public String dequeue() {
		if(isFull()) {
			resize(2 * a.length);
		}
		N--;
		return a[head++];
	}
	
    public static void main(String[] args) {
    	Prac_1314 queue = new Prac_1314();
    	queue.enqueue("a");
    	System.out.println("size: " + queue.size());
    	queue.enqueue("b");
    	System.out.println("size: " + queue.size());
    	queue.enqueue("c");
    	System.out.println("size: " + queue.size());
    	queue.enqueue("d");
    	System.out.println("size: " + queue.size());
    	queue.enqueue("e");
    	System.out.println("size: " + queue.size());
    	queue.enqueue("f");
    	System.out.println("size: " + queue.size());
    	while(!queue.isEmpty()) {
    		System.out.println(queue.dequeue() + " | size: "  + queue.size());
    	}
    }
}
