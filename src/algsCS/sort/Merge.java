package algsCS.sort;

/**
 * 归并排序 自顶向下，即化整为零
 */
public class Merge {
    private static Comparable aux[];
    private static int CUT_OFF = 7;//将归并排序替换成插入排序的 子数组长度;
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 初级版归并排序
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * 优化版归并排序
     * @param src 数组的克隆体
     * @param dist
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] src, Comparable[] dist, int lo, int hi){
        if(hi - lo <= CUT_OFF){
            //对于小规模数组，使用插入排序要比归并快
            insertion(dist, lo, hi);
        }
        int mid = (lo + hi) / 2;
        sort(dist, src, lo, mid);
        sort(dist, src, mid + 1, hi);
        //如果数组的左右两边已经是有序，则不需要进行归并了
        if(!less(src[mid + 1], src[mid])){
            System.arraycopy(src, lo, dist, lo, hi -  lo + 1);
        }
        //进行归并
        merge(src, dist, lo, mid, hi);
    }

    private static void merge(Comparable[] src, Comparable[] dist, int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                dist[k] = src[j++];
            }else if(j > hi){
                dist[k] = src[i++];
            }else if (less(src[j], src[i])){
                dist[k] = src[j++];
            } else {
                dist[k] = src[i++];
            }
        }
    }

    /**
     * 插入排序
     * @param a
     * @param lo
     * @param hi
     */
    public static void insertion(Comparable[] a, int lo, int hi) {

        //小规模使用插入排序
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {//复制数组副本
            aux[k] = a[k];
        }
        //开始归并
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }

        }
    }

    public static void main(String[] args) {
        String[] a = new String[]{"Z", "E", "G", "M", "R", "C",
                "A", "R", "E", "T"};
        merge(a, 0, (0 + 9) / 2, a.length - 1);
        show(a);

    }

    /**
     * 比较
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换位置
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
