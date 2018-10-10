package String.StringSorts;


public class MSD {
	// ������ĸ�� R=26 �����
	private static int R = 256; // ����
	private static final int M = 15; // С������л���ֵ
	private static String[] aux; // ���ݷ���ĸ�������
	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d); // ���ص����ַ����е�d��λ�õ��ַ���ASCII��
		} else {
			return -1; // ���û�оͷ��ػ򵽴�ĩβ����-1
		}
	}
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}
	
	/**
	 * @param a �ַ���
	 * @param lo �������ʼλ��
	 * @param hi �������ֹλ��
	 * @param d ��d���ַ�
	 */
	private static void sort(String[] a, int lo, int hi, int d) {
		// �Ե�d���ַ�Ϊ����a[lo]��a[hi]����
		if(hi <= lo + M) { // ���������Сʱ
			Insertion.sort(a, lo, hi, d); // �л�ʹ�ÿ�������
			return ;
		}
		int[] count = new int[R + 2]; // ����Ƶ��
		for(int i = lo; i <= hi; i++) { 
			count[charAt(a[i], d) + 2]++; 
		// �ڵ�λ���������1�����ڸ�λ�У��ⲽ������Ҫ�ټ�һ����Ϊ�����ַ�������ĩβʱ����-1�������
		
		}
		for(int r = 0; r < R + 1; r++) { // ��Ƶ��ת��Ϊ����
			count[r + 1] += count[r]; // ��ʱ��count[]�Ͳ�������¼Ƶ�ʣ�����ĳ����ĸ����ʼλ��
		}
		for(int i = lo; i <= hi; i++) { // ���ݷ���
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		}
		for(int i = lo; i <= hi; i++) { // ��д
			a[i] = aux[i - lo]; // [i - lo] ���Ǻ���ĵݹ�����lo��仯���Ѿ�����ľͲ����ٽ��д�����һ�������ȳ�·�ַ�������ĸ������
		}
		// ��һ������������ĸ
		// �����꣬�ݹ����ÿ���ַ�Ϊ����������
		for(int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}
}
