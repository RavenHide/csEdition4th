package algsCS.sort.priorityQueue;

public class HeapMinPQ<K extends Comparable<K>> implements IMinPQ<K> {
    private K[] pq;//优先队列
    private int N = 0;//节点树
    private static final int DEFAULT_CAPACITY = 2;

    public HeapMinPQ() {
        pq = (K[]) new Object[DEFAULT_CAPACITY];
    }

    public HeapMinPQ(K[] ks){
        N = ks.length + 1;
        pq = (K[]) new Object[N];

        for (int i = 0; i < ks.length; i++) {
            insert(ks[i]);
        }
    }

    public HeapMinPQ(Iterable<K> data){
        N = DEFAULT_CAPACITY;
        pq = (K[]) new Object[N];
        for (K datum : data) {
            insert(datum);
        }
    }


    /**
     * 堆上浮
     * @param k
     */
    private void swim(int k){
        while (k > 1 && less(k, k/2)){
            exch(k , k/2);
            k = k / 2;
        }
    }

    /**
     * 堆下沉
     * @param k
     */
    private void sink(int k){
        while (2 * k <= N){
            int j = 2 * k;
            if(j < N && less(j + 1, j)) j++;
            if(less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int max){
        K pq1[] = (K[]) new Object[max + 1];
        int length = max > pq.length ? pq.length : max + 1;
        for (int i = 0; i < length ; i++) {
            pq1[i] = pq[i];
        }
        pq = pq1;
    }

    @Override
    public void insert(K v) {
        if(N == pq.length - 1){
            resize(N * 2);
        }
        pq[++N] = v;
        swim(N);

    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 比较 i 和 j 对应的key的value的大小
     * @param i
     * @param j
     * @return
     */
    private boolean  less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0 ;
    }

    /**
     * 交换两元素位置
     * @param i
     * @param j
     * @return
     */
    private boolean exch(int i, int j){
        K t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        return true;
    }

    @Override
    public K min() {
        return N > 0 ? pq[1] : pq[0];
    }

    @Override
    public K delMin() {
        if(N <= 0) return null;
        K min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);

        if(N > 0 && N <= (pq.length - 1) / 4){
            resize(N * 2);
        }
        return min;
    }
}
