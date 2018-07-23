package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.sort.priorityQueue.IndexMinPQ;
import java.util.Stack;

/**
 * 迪杰斯特拉 加权最短有向路径问题
 */
public class DijkstraSP {
    private WeightDiEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private int s;
    public DijkstraSP(EdgeWeightedDiGraph G, int s) {
        this.s = s;
        edgeTo = new WeightDiEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new IndexMinPQ<>();
        pq.insert(s, 0.0);

        while(!pq.isEmpty()){
            relax(G, pq.delMin());
        }

    }

    private void relax(EdgeWeightedDiGraph G, int v){
        for (WeightDiEdge e: G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * 起点到v的权重总和
     * @param v
     * @return
     */
    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<WeightDiEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<WeightDiEdge> path = new Stack<>();

        for(int w = v; w != s; w = edgeTo[w].from()){
            path.push(edgeTo[w]);
        }
        return path;
    }


}
