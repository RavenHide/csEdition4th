package algsCS.sort.priorityQueue;

import java.util.NoSuchElementException;

public class IndexMinPQ<K extends Comparable<K>> {
    private int N;//节点数量
    private K[] keys;//存储key的数组
    private int[] pq;//优先队列pq根据key的大小来存储keys数组的索引
    private int[] qp;//pq的反索引数组，可以通过pq数组的值(keys的索引)得到pq的索引
    private int MAX_CAPACITY = 101;

    public IndexMinPQ(int capacity){
        MAX_CAPACITY = capacity + 1;
        init();
    }

    private void init(){
        N = 0;
        keys = (K[]) new Object[MAX_CAPACITY];
        pq = new int[MAX_CAPACITY];
        qp = new int[MAX_CAPACITY];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public IndexMinPQ() {
        init();
    }

    public void swim(int k){
        while (k > 1 && greater(k / 2, k)){
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void sink(int k){
        while(k <= N) {
            int j = k * 2;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

//    private void resize(int max){
//        int pq1[] = new int[max + 1];
//        int qp1[] = new int[max + 1];
//        K[] kyes1 = (K[]) new Object[max + 1];
//        int length = max > pq.length ? pq.length : max + 1;
//        for (int i = 0; i < length; i++) {
//            pq1[i] = pq[i];
//            qp1[i] = qp[i];
//            kyes1[i] = keys[i];
//        }
//        pq = pq1;
//    }

    public int minIndex(){
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public K minKey() {
        if( N <= 0) return null;

        return keys[pq[1]];
    }


    public int delMin() {
        int minKeyIndex = pq[1];
        exch(1, N--);
        sink(1);

        assert minKeyIndex == pq[N+1];

        qp[minKeyIndex] = -1;
        pq[N + 1] = -1;

        keys[minKeyIndex] = null;
        return minKeyIndex;
    }

    public void insert(int i, K v) {
        if (i < 0 || i >= MAX_CAPACITY) throw new IllegalArgumentException();
        if(N == MAX_CAPACITY - 1) throw new ArrayIndexOutOfBoundsException("array has already overflowed");
        if(contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        keys[i] = v;
        pq[++N] = i;
        qp[i] = N;
        swim(N);
    }

    public boolean contains(int i){
        assert i > 0 && i < MAX_CAPACITY;
        return qp[i] != -1;
    }


    public boolean isEmpty() {
        return N <= 0;
    }


    public int size() {
        return 0;
    }

    /**
     * 比较 i 和 j 对应的key的value的大小
     * @param i
     * @param j
     * @return
     */
    private boolean  greater(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    /**
     * 交换两元素位置
     * @param i
     * @param j
     * @return
     */
    private boolean exch(int i, int j){
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
        return true;
    }

    public K keyOf(int i){
        if (i < 0 || i >= MAX_CAPACITY) throw new IllegalArgumentException();
        if(!contains(i)) throw  new IllegalArgumentException("index is not in the priority queue");
        return keys[i];
    }

    public void changeKey(int i, K key){
        if (i < 0 || i >= MAX_CAPACITY) throw new IllegalArgumentException();
        if(!contains(i)) throw  new IllegalArgumentException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        swim(qp[i]);
    }

    public void delete(int i){
        if (i < 0 || i >= MAX_CAPACITY) throw new IllegalArgumentException();
        if(!contains(i)) throw  new IllegalArgumentException("index is not in the priority queue");
        int deleteIndex = qp[i];
        exch(deleteIndex, N--);
        swim(deleteIndex);
        sink(deleteIndex);
        qp[i] = -1;
        keys[i] = null;

    }
}
