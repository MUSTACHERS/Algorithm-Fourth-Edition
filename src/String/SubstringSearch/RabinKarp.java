package String.SubstringSearch;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private String pat; // 模式字符串（仅拉斯维加斯算法需要）
	private long patHash; // 模式字符串的散列值
	private int M; // 模式字符串的长度
	private long Q; // 一个很大的素数
	private int R = 256; // 字母表的大小
	private long RM; // R^(M-1)%Q
	public RabinKarp(String pat) {
		this.pat = pat; // 保存模式字符串（仅拉斯维加斯算法需要）
		this.M = pat.length();
		Q = longRandomPrime();
		RM = 1;
		for(int i = 1; i <= M-1; i++) {
			RM = (R * RM) % Q;
		}
		patHash = hash(pat, M);
	}
//	public boolean check(int i) { // 蒙特卡洛版本的算法（Monte Carlo Version）
//		return true; 
//	}
	
	public boolean check(String txt, int i) {
		for(int j = 0; j < M; j++) {
			if(pat.charAt(j) != txt.charAt(i + j)) {
				return false;
			}
		}
		return true;
	}
	private long hash(String key, int M) {
		// 计算key[0..M-1]的散列值
		long h = 0;
		for(int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
		}
		return h;
	}
	private int search(String txt) {
		// 在文本中查找相等的散列值
		int N = txt.length();
		long txtHash = hash(txt, M);
//		蒙特卡洛算法
//		if(patHash == txtHash && check(0)) { // 假设一开始就匹配成功
//			return 0;
//		}
//		拉斯维加斯算法，检查模式与txt(i...i-M+1)的匹配
		if(patHash == txtHash && check(txt, 0)) {
			return 0;
		}
		for(int i = M; i < N; i++) {
			// 减去第一个数字，加上最后一个数字，再次检查匹配
			txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
			txtHash = (txtHash + txt.charAt(i)) % Q;
			// 上面两句等价于下面这句，性质还是同余模定理：每一个计算后都取一次余，和所有计算结束后取余的结构一样
			// txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) * R + txt.charAt(i)) % Q;
			// 解释上面这句：txtHash - RM*txt.charAt(i-M)是代表用当前的hash值减去第一位数字；
			// +Q代表防止前面的结果为负数，*R代表乘以基数，+txt.charAt(i)代表加上最后一位数字，然后取余。
			if(patHash == txtHash) {
				if(check(txt, i-M+1)) {
					return i-M+1; // 找到匹配
				}
			}
		}
		return N; // 未找到匹配
	}
	// 一个随机31位的素数
	private static long longRandomPrime() {
		// BigInteger 可以让超过Integer范围内的数据进行运算，可以准确的表示任何大小的整数而不会丢失信息 
		// probablePrime(int bitlength, Random md) 返回有可能是具有二进制bitlength位的素数，返回可能是素数的概率为不超过2^-100
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();   // 返回最大整数的long类型
	}
}
