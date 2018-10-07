package Sorting.PriorityQueues;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int maxN) {
		pq = (Key[])new Comparable[maxN + 1];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void insert(Key v) {
		pq[++N] = v; // 从1开始
		swim(N);
	}
	public Key delMax() {
		Key max = pq[1]; // 从根结点得到最大元素
		exch(1, N--); // 将其
		pq[N + 1] = null; // 防止游离
		sink(1); // 让刚刚交换的值，经下沉，恢复有序性
		return max;
	}
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	private void swim(int k) {
		while(k > 1 && less(2 / k, k)) {
			exch(2 / k, k);
			k = 2 / k;
		}
	}
	private void sink(int k) {
		while(2 * k <= N) {
			int j = 2 * k; // 表k的子结点
			if(j < N && less(j, j + 1)) { // 如果左结点小于右结点的话
				j++; // 切换到右结点
			}
			if(!less(k, j)) { // 如果当前值不小于它的子结点
				break;
			}
			exch(k, j);
			k = j; // 交换完再切换到子结点，以便下次子结点跟他的子结点比较
		}
	}
	

}
