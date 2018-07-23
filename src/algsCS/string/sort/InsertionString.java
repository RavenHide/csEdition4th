package algsCS.string.sort;

/**
 * 字符串的插入排序
 */
public class InsertionString {

    public static void sort(String[] a, int lo, int hi, int d) {
        //进行插入排序
        for (int i = lo + 1; i <= hi; i++) {
            int j;
            String swap = a[i];
            for (j = i; j > 0 && less(swap, a[j - 1], d); j--) {
                a[j] = a[j - 1];
            }
            a[j] = swap;
        }

    }

    private static boolean less(String s1, String s2, int d) {

        return s1.substring(d).compareTo(s2.substring(d)) < 0;
    }
}
