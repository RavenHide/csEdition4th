package algsCS.string.sort;

/**
 * 基于键索引记数法的低位优先字符排序
 * 假设进行排序的字符数组的每个元素的字符长度都一致
 */
public class LSD {
    /**
     *
     * @param a
     * @param w 字符串长度
     */
    public static void sort (String[] a, int w){
        int N = a.length;
        int R = 256;
        String aux[] = new String[N];
        //从最后一个字符开始，将string.charat[d]作为键来进行键索引记数法的排序
        for (int d = w-1; d <= 0; d--) {

            int[] count = new int[R + 1];
            //计算频率
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            //将频率转成索引
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            //进行分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            //回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
}
