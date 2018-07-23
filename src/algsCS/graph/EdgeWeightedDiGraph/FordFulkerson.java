package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.basic.Queue;

/**
 * 最短增广路径算法,基于图的广度优先搜索
 */
public class FordFulkerson {
    private boolean[] marked;//剩余网络中是否存在从s到v的路径
    private FlowEdge[] edgeTo;//从s到v的最短路径上的最后一条边
    private double value;//当前最大流量

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while(hasAugmentingPath(G, s, t)){
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[t].other(t)) {
                bottle = Math.min(bottle, edgeTo[t].residualCapacityTo(t));
            }
            //增大流量
            for (int v = t; v != s; v = edgeTo[t].other(t)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t){
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> q = new Queue<>();
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()){
            int v = q.dequeue();
            for (FlowEdge edge: G.adj(v)){
                int w = edge.other(v);
                if(edge.residualCapacityTo(w) > 0 && !marked[w]){
                    edgeTo[w] = edge;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        return marked[t];
    }
}

