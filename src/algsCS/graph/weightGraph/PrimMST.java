package algsCS.graph.weightGraph;

import algsCS.basic.Queue;
import algsCS.sort.priorityQueue.IndexMinPQ;

/**
 * Prim最小生成树即使算法
 */
public class PrimMST {
    private WeightEdge[] weightEdgeTo;//距离树最短的边
    private double[] distTo;//表示edgeTo[i] 的权重
    private boolean[] marked;//进入树的顶点
    private IndexMinPQ<Double> pq;//有效横切边

    public PrimMST(EdgeWeightedGraph G) {
        weightEdgeTo = new WeightEdge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int i = 0; i < distTo.length; i++) {
                distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){
            visit(G, pq.delMin());
        }
    }
    
    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;

        for (WeightEdge weightEdge : G.adj(v)) {
            int w = weightEdge.other(v);
            if(marked[w]) continue;
            if(weightEdge.weight() < distTo[w]){
                weightEdgeTo[w] = weightEdge;
                distTo[w] = weightEdge.weight();
                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<WeightEdge> edges(){
        Queue<WeightEdge> tree = new Queue<>();
        for (int i = 0; i < weightEdgeTo.length; i++  ) {
            tree.enqueue(weightEdgeTo[i]);
        }
        return tree;
    }
    
}
