package algsCS.graph.EdgeWeightedDiGraph;

import algsCS.basic.Queue;

import java.util.Stack;

/**
 * Bellman检测负权重环的最短路径算法
 */
public class BellmanFordSP {
    private WeightDiEdge[] edgeTo;
    private double[] distTo;
    private boolean[] onQ;//顶点是否在队列
    private Queue<Integer> queue;//将要被放松的顶点
    private int cost;//放松次数
    private Iterable<WeightDiEdge> cycle;
    private int s;//起点

    public BellmanFordSP(EdgeWeightedDiGraph G, int s) {
        this.s = s;
        distTo = new double[G.V()];

        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        edgeTo = new WeightDiEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();
        cost = 0;

        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[v] = false;

        }
    }

    public void relax(EdgeWeightedDiGraph G, int v){
        for (WeightDiEdge e: G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQ[w]){
                    queue.enqueue(w);
                }
            }
            if(cost++ % G.V() == 0){//每进行V轮放松后，在下一轮放松都会进行一次findNegativeCycle
                findNegativeCycle();
            }
        }

    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<WeightDiEdge> pathTo(int v){
        if(hasNegativeCycle()) return null;
        if(!hasPathTo(v)) return null;
        Stack<WeightDiEdge> stack = new Stack<>();

        for (int w = v; w != s; w = edgeTo[w].from()) {
            stack.push(edgeTo[w]);
        }
        return stack;
    }

    public boolean hasNegativeCycle(){
        //todo
        return cycle != null;
    }

    private void findNegativeCycle(){
        int v = edgeTo.length;
        EdgeWeightedDiGraph spt = new EdgeWeightedDiGraph(v);
        for (int i = 0; i < i; i++) {
            if(edgeTo[i] != null)
                spt.addEdge(edgeTo[i]);
        }
        WeightDiCycle cf = new WeightDiCycle(spt);
        cycle = cf.cycle();
    }

    public Iterable<WeightDiEdge> negativeCycle(){
        return cycle;
    }
}
