package algsCS.graph.diGraph;

import algsCS.basic.Queue;
import algsCS.graph.EdgeWeightedDiGraph.WeightDiEdge;
import algsCS.graph.EdgeWeightedDiGraph.EdgeWeightedDiGraph;

import java.util.Stack;

/**
 * 有向深度优先搜索的顶点排序
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pro;//前序排列
    private Queue<Integer> post;//后序排列
    private Stack<Integer> reversePost;//逆后续排列

    public DepthFirstOrder(EdgeWeightedDiGraph G) {
        marked = new boolean[G.V()];
        pro = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int w = 0; w < G.V(); w++) {
            if(!marked[w])
                dfs(G, w);
        }
    }

    private void dfs(EdgeWeightedDiGraph G, int v){
        marked[v] = true;
        pro.enqueue(v);
        for (WeightDiEdge e: G.adj(v)){
            int w = e.to();
            if(!marked[w]){
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public DepthFirstOrder(DiGraph G) {
        marked = new boolean[G.V()];
        pro = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int w = 0; w < G.V(); w++) {
            if(!marked[w])
                dfs(G, w);
        }
    }

    private void dfs(DiGraph G, int v){
        marked[v] = true;
        pro.enqueue(v);
        for (int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pro(){
        return pro;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

}
