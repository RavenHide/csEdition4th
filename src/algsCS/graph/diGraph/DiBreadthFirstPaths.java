package algsCS.graph.diGraph;

import algsCS.basic.Queue;

import java.util.Stack;

/**
 * 有向广度优先搜索路径
 */
public class DiBreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    public DiBreadthFirstPaths(DiGraph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        bfs(G);
    }

    private void bfs(DiGraph G){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            marked[v] = true;
            for (int w: G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPath(int v){
        if (v < 0 || v >= marked.length) return false;
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPath(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s ; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}
