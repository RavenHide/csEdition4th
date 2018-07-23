package algsCS.search;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key keys[];
    private Value vals[];
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[])new Comparable[capacity];
        vals = (Value[])new Object[capacity];
        N = 0;
    }
    public int size(){
        return N;
    }
    public Value get(Key key){
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }
    public int rank(Key key){
        int lo = 0;
        int hi = N - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp == 0) return mid;
            else if (cmp > 0) hi = mid - 1;
            else if (cmp < 0) lo = mid + 1;
        }
        return lo;
    }

    public void put(Key key, Value val){
        if(val == null){
            delete(key);
        }
        if(N == keys.length){
            resize(2 * N);
        }
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--){
            vals[j] = vals[j - 1];
            keys[j] = keys[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Key delete(Key key){
        if(key == null) throw new IllegalArgumentException("argument to delete is null");
        if(isEmpty()) return null;

        int i = rank(key);

        if(i == N || keys[i].compareTo(key) != 0){
            return null;
        }

        Key result = keys[i];

        for (; i < N - 1; i++){
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
        }

        keys[i] = null;
        vals[i] = null;
        N--;
        if(N > 0 && N == keys.length / 4){
            resize(keys.length / 2);
        }
        return result;
    }

    private void resize(int capacity){
        assert capacity > 0;

        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];

        for(int i = 0; i < N; i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public Key min(){
        if(isEmpty()) return null;
        return keys[0];
    }
    public Key max() {
        if(isEmpty()) return null;
        return keys[N - 1];
    }

    public Key select(int k){
        if(isEmpty() || k > N - 1) return null;
        else return keys[k];
    }

}
