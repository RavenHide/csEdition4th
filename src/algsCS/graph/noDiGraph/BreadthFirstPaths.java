package algsCS.graph.noDiGraph;

import algsCS.basic.Queue;

import java.util.Stack;

/**
 * 广度优先搜索
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
    }

    /**
     * 广度优先搜索
     * @param G
     * @param s
     */
    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w: G.adj(v)){
                if(!marked[w]){
                    queue.enqueue(w);
                    edgeTo[w] = v;
                    marked[w] = true;
                }
            }
        }
    }

    public boolean hasPath(int v){
        if(v > marked.length || v < 0)
            return false;
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPath(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int w = v; w != this.s; w = edgeTo[w]){
            path.push(w);
        }
        path.push(s);

        return path;
    }

}
