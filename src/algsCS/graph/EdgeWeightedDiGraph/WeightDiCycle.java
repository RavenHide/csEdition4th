package algsCS.graph.EdgeWeightedDiGraph;

import java.util.Stack;

/**
 * 加权有向图是否是有环
 */
public class WeightDiCycle {
    private boolean[] marked;
    private WeightDiEdge[] edgeTo;
    private Stack<WeightDiEdge> cycle;
    private boolean[] onStack;

    public WeightDiCycle(EdgeWeightedDiGraph G){
        this.marked = new boolean[G.V()];
        this.edgeTo = new WeightDiEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if(marked[i]) continue;
                dfs(G, i);
        }
    }

    /**
     * 加权有向图的深度优先搜索
     * @param G
     * @param v
     */
    private void dfs(EdgeWeightedDiGraph G, int v){
        marked[v] = true;
        onStack[v] = true;
        for (WeightDiEdge e: G.adj(v)){
            if(hasCycle()) return;
            int w = e.to();
            if(!marked[w]){
                dfs(G, w);
            }else if(onStack[w]){
                cycle = new Stack<>();
                cycle.push(e);
                for (int x = v; x != w ; x = e.from()) {
                    cycle.push(edgeTo[x]);
                }

            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return this.cycle != null;
    }

    public Iterable<WeightDiEdge> cycle(){
        return cycle;
    }

}
