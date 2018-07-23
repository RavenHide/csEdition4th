package algsCS.graph.diGraph;

import algsCS.graph.noDiGraph.Graph;

/**
 * 有向深度优先搜索
 */
public class DirectedDFS {
    private boolean[] marked;

    //单点可达性
    public DirectedDFS(DiGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    //多点可达性分析
    public DirectedDFS(DiGraph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for(int w: sources){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }

    public void dfs(DiGraph G, int v){
        marked[v] = true;
        for (int w: G.adj(v)){
            if(!marked[w])
                dfs(G, w);
        }
    }

    public boolean marked(int v){
        return marked[v];
    }


}
