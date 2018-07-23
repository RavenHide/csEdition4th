package algsCS.graph.diGraph;

/**
 * 利用Kosaraju算法计算有向图的强连通分量
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int count;
    private int[] id;

    public KosarajuSCC(DiGraph G) {
        marked = new boolean[G.V()];
        count = 0;
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int w: order.reversePost()){
            if(marked[w]) return;
            dfs(G, w);
            count++;
        }
    }

    private void dfs(DiGraph G, int w) {
        marked[w] = true;
        id[w] = count;
        for (int v: G.adj(w)){
            if(!marked[v])
                dfs(G, v);
        }
    }

    private boolean stronglyConnected(int v, int w){
        if(v <0 || v >= marked.length || w < 0 || w >= marked.length) return false;
        return id[v] == id[w];
    }
}
