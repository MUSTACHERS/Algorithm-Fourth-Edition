package Context.Maxflow;

public class FlowEdge {
	private final int v;             // �ߵ����
	private final int w;             // �ߵ��յ�
	private final double capacity;   // ����
	private double flow;             // ����
	
	public FlowEdge(int v, int w, double capacity) {
		this.v = v;
		this.w = w;
		this.capacity = capacity;
		this.flow = 0.0;
	}
	
	public int from() 		 { return v; }
	public int to()	  		 { return w; }
	public double capacity() { return capacity; }
	public double flow()     { return flow; }
	public int other(int vertex) {
		if(vertex == v)      return w;
		else if(vertex == w) return v;
		else throw new RuntimeException("Illegal endpoint");
	}
	
	public double residualCapacityTo(int vertex) { // v��ʣ������
		if     (vertex == v) return flow;             // v->w������
		else if(vertex == w) return capacity - flow; // w->v������
		else throw new RuntimeException("Inconsistent edge");
	}
	public void addResidualFlowTo(int vertex, double delta) { // ��v����������delta
		if	   (vertex == v) flow -= delta;
		else if(vertex == w) flow += delta;
		else throw new RuntimeException("Inconsisten edge");
	}
	public String toString() {
		return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
	}
}
