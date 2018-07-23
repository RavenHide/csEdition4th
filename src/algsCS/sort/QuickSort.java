package algsCS.sort;

//快速排序
public class QuickSort extends Template {
    private static final int M = 7;

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 快排第一版
     *
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //最初快排
        int k = partition(a, lo, hi);
        sort(a, lo, k);
        sort(a, k + 1, hi);
    }

    /**
     * 快排优化版
     *
     * @param a
     * @param lo
     * @param hi
     */
    private void sort2(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        //小数组使用插入排序总是快的
        if (hi <= lo + M) {
            insertion(a, lo, hi);
            return;
        }

        int k = partitoin2(a, lo, hi);
        sort(a, lo, k);
        sort(a, k + 1, hi);
    }


    /**
     * 获取中位数
     *
     * @param i
     * @param j
     * @param k
     * @return
     */
    private int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j])) ?
                (less(a[j], a[k]) ? j : (less(a[i], a[k]) ? k : i))
                : (less(a[i], a[k]) ? i : (less(a[j], a[k]) ? k : j));
    }

    /**
     * patition第一版
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partition(Comparable[] a, int lo, int hi) {
        Comparable t = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], t)) if (i == hi) break;
            while (less(t, a[--j])) if (j == lo) break;
            if (i >= j) break;

            exch(a, i, j);
        }
        exch(a, lo, j);
        return -1;
    }

    /**
     * partition优化版
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partitoin2(Comparable[] a, int lo, int hi) {
        int n = hi + lo + 1;
        int m = median3(a, lo, n / 2, hi);
        exch(a, m, lo);

        Comparable t = a[lo];
        int i = lo;
        int j = hi + 1;
        while(less(a[++i], t)) {
            if(i == hi){
                exch(a, i, hi);
                return hi;
            }
        }

        while(less(t, a[--j])){
            if(j == lo){
                return lo;
            }
        }

        while(true){
            exch(a, i, j);
            while(less(a[++i], t));
            while(less(t, a[--j]));
            if(i >= j) break;
        }
        exch(a, lo, j);

        return j;
    }

    /**
     * 插入排序
     *
     * @param a
     * @param lo
     * @param hi
     */
    private void insertion(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            Comparable t = a[i];
            int j;
            for (j = i; j > lo && less(t, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }


}
