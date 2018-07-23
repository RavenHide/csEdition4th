package algsCS.unionFind;

public class UnionFind{
    private int[] id;
    private int count;
    private int[] st;//连通分量的权重
    public UnionFind(int N) {
        count = N;
        id = new int[N];
        st = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            st[i] = 1;
        }

    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * 把p所在的连通分量 和 q 所在的连通分量进行归并
     * @param p
     * @param q
     */
    public void union(int p, int q){
        weightQuickUnion(p, q);
    }


    public int find(int p) {
        return quickUnionFind(p);
    }

    //quick-find 时间是平方级别，无法处理大型数据
    private int quickFind(int p){
        return id[p];
    }
    private void quickFindUnion(int p, int q){
        int qID = quickFind(q);
        int pID = quickFind(p);

        if(qID == pID) return;
        for (int i = 0; i < id.length; i++) {
            if(id[i] == qID) id[i] = pID;
        }
        count--;
    }

    //quick-union 最坏情况下是平方级别
    private int quickUnionFind(int p){
        while(p != id[p]) p = id[p];
        return p;
    }

    private void quickUnion(int p, int q){
        int pRoot = quickUnionFind(p);
        int qRoot = quickUnionFind(q);
        if(pRoot == qRoot) return;

        id[qRoot] = pRoot;
        count--;
    }

    //加权quick-uinon
    private void weightQuickUnion(int p, int q){
        int pRoot = quickUnionFind(p);
        int qRoot = quickUnionFind(q);

        if(pRoot == qRoot) return;

        if(st[pRoot] < st[qRoot]){
            id[pRoot] = qRoot;
            st[qRoot] += st[pRoot];
        }else {
            id[qRoot] = pRoot;
            st[pRoot] += st[qRoot];
        }
        count--;

    }



}
