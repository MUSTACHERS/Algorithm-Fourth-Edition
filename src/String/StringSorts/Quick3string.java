package String.StringSorts;

public class Quick3string {
	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d);
		} else {
			return -1;
		}
	}
	public void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
	private void sort(String[] a, int lo, int hi, int d) {
		if(hi <= lo) {
			return ;
		}
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d); // 切分点
		int i = lo + 1; // 从切分点的下一个位置开始
		while(i <= gt) {
			int t = charAt(a[i], d);
			if(t < v) {
				exch(a, lt++, i++); // 当前位置的元素跟前一个元素交换
			} else if(t > v) {
				exch(a, i, gt--); // 当前位置的元素跟最后一个元素交换
			} else {
				i++;
			}
		}
//		切分成三部分，再排序
		sort(a, lo, lt - 1, d); //  [lo, lt - 1] 前面部分  当中还需要再排序切分
		if(v >= 0) { // [lt, gt] 中间部分
			sort(a, lt, gt, d + 1); // 中间部分，这些字符串的首字母已经排序完，进入下一个字母的排序
		}
		sort(a, gt + 1, hi, d); // [gt + 1, hi] 后面部分
	}
	private void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
