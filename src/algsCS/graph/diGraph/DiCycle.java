package algsCS.graph.diGraph;

import java.util.Stack;

/**
 * 深度优先搜索查找有向环
 */
public class DiCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DiCycle(DiGraph G) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int w = 0; w < G.V(); w++) {
            if(!marked[w])
                dfs(G, w);
        }
    }



    /**
     * 无权有向图的深度优先搜索
     * @param G
     * @param w
     */
    private void dfs(DiGraph G, int w) {
        marked[w] = true;
        onStack[w] = true;
        for (int v : G.adj(w)){
            if(hasCycle()) return ;
            if(!marked[v]) {
                edgeTo[v] = w;
                dfs(G, v);
            }else if(onStack[v]){

                cycle = new Stack<>();
                for(int x = w; x != v; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(w);
            }
        }
        onStack[w] = false;
    }

    public boolean hasCycle(){
        return this.cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

}
