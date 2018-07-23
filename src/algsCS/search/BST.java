package algsCS.search;


/**
 * 二叉查找树
 */
public class BST<Key extends Comparable<Key>, Value>{
    private Node root;//根节点

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;//已该节点为根节点的子树中的节点总数

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key , Value val){
        put(root, key, val);
    }

    private Node put(Node x, Key key, Value val){
        if(x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if(cmp > 0)x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        if(root == null) return null;
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        if(root == null) return null;
        return max(root).key;
    }

    private Node max(Node node){
        if(node.right == null) return node;
        return max(node.right);
    }

    public Node floor(Node x, Key key){
        if(x == null)return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        else if(cmp < 0)return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t == null) return x;
        else return t;
    }

    public Key select(int k){
        Node node = select(root, k);
        if(node == null) return null;
        return node.key;
    }

    private Node select(Node node, int k){
        if(node == null){
            return null ;
        }
        int size = size(node.left);
        if(size > k) return select(node.left, k);
        else if (size == k) return node;
        else return select(node.right, k - size - 1);
    }

    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node node){
        int cmp = node.key.compareTo(key);
        if(node == null) return 0;

        if(cmp == 0) return size(node.left);
        else if(cmp < 0) return 1 + size((node.left)) + rank(key, node.right);
        else return rank(key, node.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    public Node deleteMin(Node node){
        if(node.left == null){
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public void delete(Key key){
            delete(key, root);
    }

    private Node delete(Key key, Node node){
        if(node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp > 0) node.right = delete(key, node.right);
        else if(cmp < 0 ) node.left =  delete(key, node.left);
        else{
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;
            Node t = node;
            node = min(node.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

//    public Iterable<Key> keys(){
//
//    }
//
//    public Iterable<Key> keys(Key lo, Key hi){
//
//        Queue<Key> queue = new Queue<>();
//        keys(root, queue, lo, hi);
//        return queue;
//    }
//
//    private void keys(Node x, Queue queue, Key lo, Key hi){
//        if(x == null) return;
//
//        int cmpLo = lo.compareTo(x.key);
//        int cmpHi = hi.compareTo(x.key);
//
//        if(cmpLo < 0) keys(x.left, queue, lo, hi);
//        if(cmpLo <= 0 && cmpHi >= 0) queue.enqueue(x);
//        if(cmpHi > 0)keys(x.right, queue, lo, hi);
//    }



}
