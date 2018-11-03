package Context.Maxflow;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class FlowNetwork {
	private final int V;         // ��������
	private int E;               // �ߵ�����
	private Bag<FlowEdge>[] adj; // �ڽӱ�
	
	public FlowNetwork(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<FlowEdge>[]) new Bag[V]; 
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<FlowEdge>();
		}
	}
	public FlowNetwork(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double capacity = in.readDouble();
			addEdge(new FlowEdge(v, w, capacity));
		}
	}
	public int V() { return V; }
	public int E() { return E; }
	public void addEdge(FlowEdge e) { // ˫��
		adj[e.from()].add(e);
		adj[e.to()].add(e);
		E++;
	}
	public Iterable<FlowEdge> adj(int v) {
		return adj[v];
	}
	public String toString() {
		String NEWLINE = System.getProperty("line.separator"); // ���У�����д��ֹ�ڲ�ͬϵͳ���ִ���
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(v + ": ");
			for(FlowEdge e : adj[v]) {
				if(e.to() != v) s.append(e + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
