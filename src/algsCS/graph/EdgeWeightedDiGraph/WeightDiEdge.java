package algsCS.graph.EdgeWeightedDiGraph;

public class WeightDiEdge {
    private double weight;
    private int v;
    private int w;
    ;

    public WeightDiEdge(int from, int to, double weight) {
        this.weight = weight;
        this.v = from;
        this.w = to;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

}
