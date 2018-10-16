package String.SubstringSearch;

public class BoyerMoore {
	private int[] right;
	private String pat;
	public BoyerMoore(String pat) {
		// 计算跳跃表
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		right = new int[R];
		for(int c = 0; c < R; c++) {
			right[c] = -1; // 不包含在模式字符串种的字符的值为-1
		}
		for(int j = 0; j < M; j++) {
			right[pat.charAt(j)] = j;
		}
	}
	
	public int search(String txt) {
		// 在txt种查找模式字符串
		int N = txt.length();
		int M = pat.length();
		int skip;
		for(int i = 0; i < N-M; i += skip) {
			// 模式字符串和文本在位置i匹配吗？
			skip = 0;
			for(int j = M-1; j >= 0; j--) {
				if(pat.charAt(j) != txt.charAt(j + i)) { // 匹配失败的处理
					// 这句包含了两种处理：一种是txt字符存在于pat中，一种是txt字符不存在于pat中。
					// 如果存在，我们把 txt.charAt(j+i) 当作索引传入 right[],
					// 		会找到存在在pat字符的位置，然后j-right[txt.charAt(j+i)],下次循环时会跳到skip的位置，也就是txt字符与pat字符相等的位置
					// 如果不存在，我们还是这样 传入，只不过在pat中找不到会返回 -1，所以 skip=j+1,那么下次i+j+1的位置
					skip = j - right[txt.charAt(j + i)];
					// 如果上面这条语句不能增大i，就直接将i+1来保证模式字符串至少向右移动了一个位置 
					// 为什么要这样，因为当pat出现多个相同字符，right只会记该字符最后出现的位置，
					// 所以假设本来就已经有字符跟模式最后一个字符匹配，在中途又遇到一个跟模式最后一个字符匹配但不是pat当前位置匹配的字符，并且上面的原因
					// 所以又会把最后一个字符移动到当前txt的位置，所以 skip=j-right[]=0，所以匹配失败后会造成死循环（无限不匹配）因为i也不会变化
					// 所以，我们至少要保证 i+1
					if(skip < 1) {
						skip = 1;
					}
					break;
				}
			}
			if(skip == 0) { // 匹配成功
				return i;
			}
		}
		return N; // 匹配失败
	}
}
