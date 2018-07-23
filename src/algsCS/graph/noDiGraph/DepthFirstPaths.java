package algsCS.graph.noDiGraph;

import java.util.Stack;

/**
 * 深度优先搜索解决路径问题
 */
public class DepthFirstPaths {
    private boolean[] marked;

    private int[] edageTo;
    private int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];;
        edageTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    /**
     * 深度优先搜索
     * @param G
     * @param s
     */
    private void dfs(Graph G, int s){
        marked[s] = true;
        for(int v: G.adj(s)){
            if(!marked[v]){
                edageTo[v] = s;
                dfs(G, v);
            }
        }
    }

    public boolean hasPathTo(int v){
        if(v < 0 || v > marked.length)
            return false;
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != this.s; x = edageTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
