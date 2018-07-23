package algsCS.sort;
//选择排序
public class Selection {

    public static void sort1(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            for(int j = i + 1; j < a.length; j++){
                if(less(a[j], a[i])){
                    exch(a, j, i);
                }
            }
        }
    }

    public static void sort(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            int min = i;
            for(int j = i + 1; j < a.length; j++){
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    /**
     * 比较v是否小于w
     * comparable接口比较大小的特点是：
     *  v.compareTo(w)的返回值只有-1, 0, 1三种结果
     *  v > w 则返回1, v < w 则返回 -1, v == w 则返回 0
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换位置
     * @param a 数组,这里选择用Comparable类型数组，是因为int、long、String一些基本类型，java已经
     *          帮我们实现Comparable接口，所以直接声明为Comparable数组，只是为了方便进行面向对象的比较
     *          如果你需要将实体进行排序，可以自行实现Comparable接口。
     * @param i 位置索引
     * @param j 位置索引
     */
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){

    }
}
