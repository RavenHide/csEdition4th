package algsCS.graph.diGraph;

import java.util.Stack;

/**
 * 有向深度优先搜索路径
 */
public class DiDepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DiDepthFirstPath(DiGraph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(DiGraph G, int v){
        marked[v] = true;
        for (int w: G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPath(int v){
        if(v < 0 || v >= marked.length) return false;
        return marked[v];
    }


    public Iterable<Integer> pathTo(int v){
        if(!hasPath(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }

}
