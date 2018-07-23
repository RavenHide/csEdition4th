package algsCS.graph.weightGraph;

import algsCS.basic.Queue;
import algsCS.sort.priorityQueue.HeapMinPQ;
import algsCS.unionFind.UnionFind;

/**
 * Kruskal最小生成树算法
 */
public class KruskalMST {
    private Queue<WeightEdge> mst;//存储最小生成树的队列

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        HeapMinPQ<WeightEdge> pq = new HeapMinPQ<WeightEdge>(G.edges());
        UnionFind uf = new UnionFind(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1){
            WeightEdge min = pq.delMin();
            int v = min.either();
            int w = min.other(v);
            if(uf.connected(v, w)) continue;

            uf.union(v, w);
            mst.enqueue(min);
        }
    }

    public Iterable<WeightEdge> edges(){
        return mst;
    }
}
