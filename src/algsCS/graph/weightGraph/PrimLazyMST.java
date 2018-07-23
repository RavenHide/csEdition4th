package algsCS.graph.weightGraph;

import algsCS.basic.Queue;
import algsCS.sort.priorityQueue.HeapMinPQ;

/**
 * Prim最小生成树延时实现
 */
public class PrimLazyMST {
    private boolean marked[];//标记最小生成树顶点
    private Queue<WeightEdge> mst;//最小生成树的边
    private HeapMinPQ<WeightEdge> pq;//横切边的优先队列

    public PrimLazyMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq  = new HeapMinPQ<>();
        visit(G, 0);
        while (!pq.isEmpty()){
            WeightEdge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for (WeightEdge e: G.adj(v)){
            if(!marked[e.other(v)])
                pq.insert(e);
        }
    }

    public Iterable<WeightEdge> edges(){
        return mst;
    }

}
