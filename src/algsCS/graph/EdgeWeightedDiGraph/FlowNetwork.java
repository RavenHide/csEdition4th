package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.basic.Bag;

public class FlowNetwork {
    private int V;//顶点数
    private int E;//边数
    private Bag<FlowEdge> adjs[];//邻接表

    public FlowNetwork(int v) {
        V = v;
        this.E = 0;
        adjs = new Bag[V];
        for (int i = 0; i < V; i++) {
            adjs[i] = new Bag<>();
        }
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return this.E;
    }

    public void addEdge(FlowEdge edge){
        adjs[edge.from()].add(edge);
        E++;
    }

    public Iterable<FlowEdge> adj(int v){
        return adjs[v];
    }

    public Iterable<FlowEdge> edges(){
        Bag<FlowEdge> result = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (FlowEdge edge : adjs[v]){
                result.add(edge);
            }
        }
        return  result;
    }
}
