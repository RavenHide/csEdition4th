package algsCS.graph.noDiGraph;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        count = 0;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s){
        marked[s] = true;
        count++;
        for(int v: graph.adj(s)){
            if(!marked[v]) dfs(graph, v);
        }

    }

    /**
     * 判断顶点是否被标记过
     * @param w
     * @return
     */
    public boolean marked(int w){
        if(w >= marked.length) return false;
        return marked[w];
    }

    public int count(){
        return count;
    }
}
