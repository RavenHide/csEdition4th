package algsCS.sort.priorityQueue;

/**
 * 堆：优先队列
 * @param <K>
 */
public class HeapMaxPQ<K extends Comparable<K>> implements IMaxPQ<K>{
    private K pq[];
    private int N = 0;

    /**
     * 堆上浮
     */
    private void swim(int k){
        while(k > 1 && less(k / 2, k)){
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 堆下浮
     */
    private void sink(int k){
        while(2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(j, k);
            k = j;
        }
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
    public K max() {
        return N > 0 ? pq[1]: pq[0];
    }

    private void resize(int max){
        K pq1[] = (K[])new Object[max + 1];
        int length = max > pq.length ? pq.length : max + 1;
        for(int i = 0; i < length; i++){
            pq1[i] = pq[i];
        }
        pq = pq1;
    }

    @Override
    public K delMax() {
        if(N <= 0) return null;
        K max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);

        if(N > 0 && N == (pq.length -1 ) / 4){
            resize(N * 2);
        }
        return max;
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

}
