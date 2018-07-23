package algsCS.graph.weightGraph;

/**
 * 带权重的边
 */
public class WeightEdge implements Comparable<WeightEdge>{
    private double weight;//权重
    private int v;//顶点
    private int w;//另一顶点

    public WeightEdge(double weight, int v, int w) {
        this.weight = weight;
        this.v = v;
        this.w = w;
    }

    public double weight(){
        return weight;
    }

    /**
     * 当两个顶点都是未知的时候，可以用这个方法获取其中一个点，接着可以调用other获取另一个顶点
     * @return
     */
    public int either(){
        return v;
    }


    public int other(int vertex){
        if(vertex == this.v) return w;
        else if(vertex == this.w) return v;
        else throw new RuntimeException("Inconsistent edge");

    }
    @Override
    public int compareTo(WeightEdge o) {
        if(this.weight > o.weight()) return 1;
        else if(this.weight == o.weight()) return 0;
        else return -1;
    }
}
