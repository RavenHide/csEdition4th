package algsCS.search;

public class SequentialSearchST<Key, Value> implements IST<Key, Value>{
    private Node first;
    private int size = 0;
    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value val) {
        if(val == null){
            delete(key);
            return;
        }
        for(Node x = first; x != null; x = x.next){
            if(x.key.equals(key)){
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        size++;
    }

    @Override
    public Value get(Key key) {
        for( Node x = first; x != null; x = x.next){
            if(x.key.equals(key)) return x.val;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node x1 = first;
        if(x1.key.equals(key)){
            first = x1.next;
            x1.next = null;
            size--;
            return;
        }
        Node old = x1;
        for(x1 = old.next; x1 != null; old = x1, x1 = x1.next){
            if(x1.key.equals(key) ){
                old.next = x1.next;
                x1.next = null;
                size--;
                return;
            }
        }
    }

    @Override
    public boolean contains(Key key) {
        for(Node x = first; x != null; x = x.next){
            if(x.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }



}
