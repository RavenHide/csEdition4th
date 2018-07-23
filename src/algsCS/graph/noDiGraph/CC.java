package algsCS.graph.noDiGraph;

/**
 * 连通分量
 */
public class CC {
    private boolean[] marked;
    private int count;
    private int[] id;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;

        for (int i = 0; i < G.V(); i++) {
            if(!marked[i]){
                dfs(G, i);
                count++;
            }
        }
    }

    /**
     * 深度优先搜索
     * @param G
     * @param v
     */
    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;

        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }

    /**
     * 判断两点是否是连通的
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w){
        assert !isIndexOutOfBound(v);
        assert !isIndexOutOfBound(w);

        return id[v] == id[w];
    }

    /**
     * 返回顶点对应的连通组号
     * @param v
     * @return
     */
    public int id(int v){
       assert !isIndexOutOfBound(v);
       return id[v];
    }

    /**
     * 顶点v是否合法
     * @param v
     * @return
     */
    public boolean isIndexOutOfBound(int v){
        if(v < 0 || v >= marked.length)
            return true;
        return false;
    }

    /**
     * 返回连通组的数量
     * @return
     */
    public int count(){
        return count;
    }
}
