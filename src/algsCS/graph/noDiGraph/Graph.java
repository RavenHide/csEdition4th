package algsCS.graph.noDiGraph;

import algsCS.basic.Bag;

public class Graph {
    private final int V; //顶点数 vertex
    private int E; //边的数量 edge
    private Bag<Integer>[] adj; //邻接表

    public Graph(int v) {
        this.V = v;
        this.E = 0;
        adj = new Bag[V];
        for(int i = 0; i < v; v++){
            adj[v] = new Bag<>();
        }
    }

    /**
     * 返回顶点数
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * 返回边数
     * @return
     */
    public int E(){
        return E;
    }

    /**
     * 添加一条边
     * @param v
     * @param w
     */
    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 返回邻边的遍历器
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
