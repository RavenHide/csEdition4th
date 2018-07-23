package algsCS.sort;

public class HeapSort extends Template{

    @Override
    public void sort(Comparable[] a) {
        //假设数组下标从1开始
        int N = a.length - 1;
        //构造堆
        for(int k = N / 2; k >= 1; k--){
            sink(a, k, N);
        }
        while(N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    /**
     * 堆上浮
     * @param a
     * @param k
     */
    private void swim(Comparable[] a, int k){
        while(k > 1 && less(k / 2, k)){
            exch(a, k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 堆下沉
     * @param a
     * @param k
     */
    private void sink(Comparable[] a, int k, int N){
        while(2 * k <= N){
            int j = 2 * k;
            if((j + 1 < N) && less(j, j + 1)) j++;
            if(!less(k, j)) break;
            exch(a, k, j);
        }
    }
}
