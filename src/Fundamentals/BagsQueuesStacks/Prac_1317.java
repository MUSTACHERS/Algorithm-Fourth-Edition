package Fundamentals.BagsQueuesStacks;

import java.util.Scanner;

class Transaction {
	private final String name;
	private final Date date;
	private final double amount;
	
	public Transaction(String name, Date date, double amount) {
		this.name = name;
		this.date = date;
		this.amount = amount;
	}
	public Transaction(String s) {
		String[] temp = s.split("\\s+");
		if(temp.length != 3) {
			throw new IllegalArgumentException("Argument illegal: " + s);
		}
		this.name = temp[0];
		this.date = new Date(temp[1]);
		this.amount = Double.parseDouble(temp[2]);
	}
	public String getName() {
		return this.name;
	}
	public Date getDate() {
		return this.date;
	}
	public double getAmount() {
		return this.amount;
	}
    public String toString(){
        return this.name + " " + this.date + " " + this.amount;
    }
	public static Transaction[] readTransaction(String s) {
		String[] temp = s.split("\\,");
		int n = temp.length;
		Queue<Transaction> queue = new Queue<Transaction>();
		for(int i = 0; i < n; i++) {
			queue.enqueue(new Transaction(temp[i]));
		}
		Transaction[] tran = new Transaction[n];
		for(int i = 0; i < n; i ++) {
			tran[i] = queue.dequeue();
		}
		return tran;
	}
}

public class Prac_1317 {
//	使用 , 来分隔 
	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		String t = null;
		while(sca.hasNext()) {
			t = sca.nextLine();
		}
		Transaction[] tran = Transaction.readTransaction(t);
		for(int i = 0; i < tran.length; i++) {
			System.out.println(tran[i]);
		}
	}
}
