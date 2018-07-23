package algsCS.sort;
//插入排序
public class Insertion {

    public static void sort1(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }

        }
    }

    public static void sort(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            //普通插入
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
            //优化之后，减少数组访问次数约一半
//            Comparable temp = a[i];
//            int j;
//            for(j = i; j > 0 && less(temp, a[j-1]); j--){
//                a[j] = a[j-1];
//            }
//            a[j] = temp;
        }
    }

    /**
     * 比较
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换位置
     * @param a
     * @param i
     * @param j
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
