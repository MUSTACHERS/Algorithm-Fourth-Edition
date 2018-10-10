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
		int v = charAt(a[lo], d); // �зֵ�
		int i = lo + 1; // ���зֵ����һ��λ�ÿ�ʼ
		while(i <= gt) {
			int t = charAt(a[i], d);
			if(t < v) {
				exch(a, lt++, i++); // ��ǰλ�õ�Ԫ�ظ�ǰһ��Ԫ�ؽ���
			} else if(t > v) {
				exch(a, i, gt--); // ��ǰλ�õ�Ԫ�ظ����һ��Ԫ�ؽ���
			} else {
				i++;
			}
		}
//		�зֳ������֣�������
		sort(a, lo, lt - 1, d); //  [lo, lt - 1] ǰ�沿��  ���л���Ҫ�������з�
		if(v >= 0) { // [lt, gt] �м䲿��
			sort(a, lt, gt, d + 1); // �м䲿�֣���Щ�ַ���������ĸ�Ѿ������꣬������һ����ĸ������
		}
		sort(a, gt + 1, hi, d); // [gt + 1, hi] ���沿��
	}
	private void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
