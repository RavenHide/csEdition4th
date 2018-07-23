package algsCS.sort;

public abstract class Template {
    public abstract void sort(Comparable[] a);

    /**
     * 比较
     * @param v
     * @param w
     * @return
     */
    protected  boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换位置
     * @param a
     * @param i
     * @param j
     */
    protected  void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected   void show(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public  boolean isSorted(Comparable[] a){
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
