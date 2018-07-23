package algsCS.graph.EdgeWeightedDiGraph;

public class FlowEdge {
    private int v, w;
    private double cap, flow;//容量， 流量
    public FlowEdge(int from, int to, double cap) {
        this.v = from;
        this.w = to;
        this.cap = cap;
        this.flow = 0.0;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public int other(int v){
        if(v == this.v) return w;
        else return v;
    }

    public double capacity(){
        return cap;
    }

    public double flow(){
        return this.flow;
    }

    public double residualCapacityTo(int vertex){
        if(vertex == v) return flow;
        else if(vertex == w) return cap - flow;
        return -1;
    }

    public void addResidualFlowTo(int vertext, double delta){
        if(vertext ==  v) flow -= delta;
        else if(vertext == w) flow += delta;
    }



}
