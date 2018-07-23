package algsCS.graph.diGraph;

import algsCS.graph.EdgeWeightedDiGraph.EdgeWeightedDiGraph;
import algsCS.graph.EdgeWeightedDiGraph.WeightDiCycle;

/**
 * 有向图的拓扑排序
 */
public class TopoLogical {
    private Iterable<Integer> order;

    public TopoLogical(DiGraph G) {
        DiCycle diCycle = new DiCycle(G);
        if(!diCycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }
    //加权有向图的拓扑排序
    public TopoLogical(EdgeWeightedDiGraph G){
        WeightDiCycle weightDiCycle = new WeightDiCycle(G);
        if(!weightDiCycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order != null;
    }
}
