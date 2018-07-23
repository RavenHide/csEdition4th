package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.graph.diGraph.TopoLogical;

import java.util.Stack;

/**
 * 无环加权有向图单点最短路径
 * 寻找最长路径只需要在distTo[]初始化为 Double.NEGATIVE_INFINITY 和改变relax的比较方向
 */
public class AcyclicSP {
    private WeightDiEdge[] edgeTo;
    private double[] distTo;
    private int s;
    public AcyclicSP(EdgeWeightedDiGraph G, int s) {
        this.s = s;
        edgeTo = new WeightDiEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {

            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        TopoLogical topoLogical = new TopoLogical(G);
        for (int v: topoLogical.order()){
            relax(G, v);
        }

    }

    private void relax(EdgeWeightedDiGraph G, int v){
        for (WeightDiEdge e: G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPath(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<WeightDiEdge> pathTo(int v){
        if(!hasPath(v)) return null;
        Stack<WeightDiEdge> path = new Stack<>();
        for(int w = v; v != this.s; v = edgeTo[v].from()){
            path.push(edgeTo[w]);
        }
        return path;
    }
}
