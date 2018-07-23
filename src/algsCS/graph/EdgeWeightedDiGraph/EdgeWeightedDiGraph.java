package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.basic.Bag;

/**
 * 加权有向图
 */
public class EdgeWeightedDiGraph {
    private int V;//顶点数
    private int E; //边数
    private Bag<WeightDiEdge>[] adjs;//邻接表

    public EdgeWeightedDiGraph(int v) {
        V = v;
        E = 0;
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

    public void addEdge(WeightDiEdge edge){
        adjs[edge.from()].add(edge);
        E++;
    }

    public Iterable<WeightDiEdge> adj(int v){
        return adjs[v];
    }

    public Iterable<WeightDiEdge> edges(){
        Bag<WeightDiEdge> result = new Bag<>();
        for (int i = 0; i < V; i++) {

            for(WeightDiEdge e : adjs[i]){
                result.add(e);
            }

        }
        return result;
    }
}
