package algsCS.graph.noDiGraph;

/**
 * 判断图是否是无环图，假设不存在平行边和自环
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        hasCycle = false;
        for (int i = 0; i < G.V(); i++) {
            if(!marked[i])
                dfs(G, i, i);
        }
    }

    private void dfs(Graph G, int v, int s){
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w, s);
            }else if(w != s) hasCycle = true;
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }
}
