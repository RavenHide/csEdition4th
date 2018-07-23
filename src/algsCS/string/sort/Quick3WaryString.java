package algsCS.string.sort;

/**
 * 字符串的三向快排
 */
public class Quick3WaryString {
    private static final int M = 15;

    public void sort(String[] a) {

    }

    /**
     * 根据索引从字符串中返回一个字符
     *
     * @param s
     * @param d
     * @return
     */
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private void exch(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            InsertionString.sort(a, lo, hi, d);
        }
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int w = charAt(a[i], d);
            if (w < v) exch(a, lt++, i++);
            else if (w > v) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }
}
