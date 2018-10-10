package String.StringSorts;


public class MSD {
	// 对于字母表 R=26 会更好
	private static int R = 256; // 基数
	private static final int M = 15; // 小数组的切换阈值
	private static String[] aux; // 数据分类的辅助数组
	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d); // 返回的是字符串中第d个位置的字符的ASCII码
		} else {
			return -1; // 如果没有就返回或到达末尾返回-1
		}
	}
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}
	
	/**
	 * @param a 字符串
	 * @param lo 排序的起始位置
	 * @param hi 排序的终止位置
	 * @param d 第d个字符
	 */
	private static void sort(String[] a, int lo, int hi, int d) {
		// 以第d个字符为键将a[lo]至a[hi]排列
		if(hi <= lo + M) { // 当子数组较小时
			Insertion.sort(a, lo, hi, d); // 切换使用快速排序
			return ;
		}
		int[] count = new int[R + 2]; // 计算频率
		for(int i = lo; i <= hi; i++) { 
			count[charAt(a[i], d) + 2]++; 
		// 在低位中这操作加1，而在高位中，这步操作需要再加一，是为处理当字符串到达末尾时返回-1的情况。
		
		}
		for(int r = 0; r < R + 1; r++) { // 将频率转换为索引
			count[r + 1] += count[r]; // 此时的count[]就不是来记录频率，而是某个字母的起始位置
		}
		for(int i = lo; i <= hi; i++) { // 数据分类
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		}
		for(int i = lo; i <= hi; i++) { // 回写
			a[i] = aux[i - lo]; // [i - lo] 考虑后面的递归排序，lo会变化，已经处理的就不会再进行处理，第一次我们先出路字符串首字母的排序
		}
		// 第一次排序处理首字母
		// 处理完，递归的以每个字符为键进行排序
		for(int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}
}
