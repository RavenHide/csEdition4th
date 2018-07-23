package algsCS.graph.diGraph;

import algsCS.basic.Bag;

/**
 * 有向图
 */
public class DiGraph {
    private final int V;
    private int E;
    private Bag<Integer> adj[];

    public DiGraph(int v) {
        V = v;
        E = 0;
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        assert !isIndexOutOfBounds(v);
        assert !isIndexOutOfBounds(w);
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        assert !isIndexOutOfBounds(v);
        return adj[v];
    }

    public boolean isIndexOutOfBounds(int v){
        return v < 0 || v >= V;
    }

    public DiGraph reverse(){
        DiGraph R = new DiGraph(V);
        for (int i = 0; i < V; i++) {
            for(int v: adj(i)){
                R.addEdge(v, i);
            }
        }
        return R;
    }

}
