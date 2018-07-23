package algsCS.string.sort;

/**
 * 高位优先字符排序
 */
public class HSD {
    private static int R = 256;
    private static final int M = 0;    //小数组切分阈值
    private static String aux[];


    /**
     * 根据索引从字符串中返回一个字符
     * @param s
     * @param d
     * @return
     */
    private static int charAt(String s, int d){
        if(d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static boolean less(String s1,  String s2, int d){

        return s1.substring(d).compareTo(s2.substring(d)) < 0;
    }

    private static void sort(String[] a, int lo, int hi, int d){
        if(hi <= lo + M){
            //进行插入排序
            InsertionString.sort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2];
        //统计频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        //转换为索引
        for(int i = 0; i < R + 1; i++){
            count[i + 1] += count[i];
        }
        //分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        //回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i-lo];
        }
        //多分法进行排序
        //键索引记数法在这里类似如快排的partition
        //现在分别对这些分组进行排序
        for (int i = 0; i < R; i++) {
            sort(a, lo + count[i], lo + count[i + 1] -1 , d + 1);
        }
    }

}
