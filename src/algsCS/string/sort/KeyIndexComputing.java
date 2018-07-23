package algsCS.string.sort;

/**
 * 键索引记数法 排序
 */
public class KeyIndexComputing<K> {
    int N;
    Item<K>[] aux;//排序后的字符数组
    int[] count;//计算频率

    /**
     *
     * @param a
     * @param R  = 最大组号 + 1
     */
    public KeyIndexComputing(Item<K>[] a, int R) {
        N = a.length;
        aux = new Item[N];

        count = new int[R + 1];
        //计算频率
        for (int i = 0; i < a.length; i++) {
            count[a[i].key() + 1]++;
        }
        //将频率转换成索引
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }
        //将元素分类排序
        for (int i = 0; i < N; i++) {
            int key = a[i].key();
            aux[count[key]++] = a[i];
        }
        //将结果回写
        for (int i = 0; i < N; i++) {
            a[i] = aux[i];
        }
    }


}
