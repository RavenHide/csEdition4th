package algsCS.graph.noDiGraph;

/**
 * 判断图是否存在 任意一条边的两个端点的颜色都不一致
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] colors;
    private boolean hasTwoColor;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        colors = new boolean[G.V()];
        hasTwoColor = true;
        for (int i = 0; i < G.V(); i++) {
            if(!marked[i]) {
                colors[i] = true;
                dfs(G, i);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;

        for(int w: G.adj(v)){
            if(!marked[v]){
                colors[w] = !colors[v];
                dfs(G, w);
            }else if(colors[w] == colors[v]) hasTwoColor = false;
        }
    }

    public boolean hasTwoColor(){
        return hasTwoColor;
    }
}
