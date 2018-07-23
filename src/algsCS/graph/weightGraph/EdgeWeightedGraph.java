package algsCS.graph.weightGraph;

import algsCS.basic.Bag;

/**
 * 权重无向图
 */
public class EdgeWeightedGraph {
    private int V;//顶点数
    private int E;//边总数
    private Bag<WeightEdge>[] adj;//邻接表

    public EdgeWeightedGraph(int v) {
        V = v;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(WeightEdge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    /**
     * 返回v点所有邻边
     * @param v
     * @return
     */
    public Iterable<WeightEdge> adj(int v){
        return adj[v];
    }

    /**
     * 返回所有边
     * @return
     */
    public Iterable<WeightEdge> edges(){
        Bag<WeightEdge> result =  new Bag<>();
        for (int v = 0; v < V; v++) {
            for (WeightEdge weightEdge : adj[v]) {
                if(weightEdge.other(v) > v) result.add(weightEdge);
            }
        }
        return result;
    }
}
