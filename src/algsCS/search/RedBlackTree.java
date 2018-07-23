package algsCS.search;

import java.util.NoSuchElementException;

//红黑树
public class RedBlackTree<Key extends Comparable<Key>, Val>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    public Node root;

    private Node balance(Node h){
        if(isRed(h.right)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    /**
     * 左旋转
     * @param h
     * @return
     */
    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 右旋转
     * @param h
     * @return
     */
    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 当左子树以及左子树的左子树为黑时，调用moveRedLeft使h的左子树或者其子数变为红树
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h){
        flipColors(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 当右子树和右子树的左子树是黑树时，通过moveRedRight使h的右子树为红树
     * @param h
     * @return
     */
    private Node moveRedRight(Node h){
        flipColors(h);
        if(isRed(h.left.left)){
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 将节点转为红色，其左右子节点转为黑色
     * @param x
     */
    void flipColors(Node x){
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        x.color = !x.color;

    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");

        if(!contain(key)) return;

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if(root != null) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }else {
            if(isRed(h.left))
                h = rotateRight(h);
            if(key.compareTo(h.key) == 0 && h.right == null)
                return null;
            if(!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0){
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }else
                h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public boolean isEmpty(){
        return root == null ? true : false;
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException("root is null");
        return min(root).key;
    }

    private Node min(Node h){
        if(h.left == null)
            return h;
        return min(h.left);
    }

    /**
     * 是否含有该key
     * @param key
     * @return
     */
    public boolean contain(Key key){
        return get(key) == null ? false : true;
    }

    /**
     * 获取一个节点
     * @param key
     * @return
     */
    public Node get(Key key){
        return get(root, key);
    }

    /**
     * 获取一个节点
     * @param x
     * @param key
     * @return
     */
    private Node get(Node x, Key key){
        if(x == null)
            return null;
        int cmp = x.key.compareTo(key);
        if(cmp > 0) return get(x.left, key);
        else if (cmp < 0) return get(x.right, key);
        else return x;

    }

    /**
     * 删除最大节点
     */
    public void deleMax(){
        if(root == null) throw new NoSuchElementException("根节点为空");

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if(root != null) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if(isRed(h.left))
            h = rotateRight(h);

        if(h.right == null)
            return null;

        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }
        h.right = deleteMin(h.right);
        return balance(h);
    }

    /**
     * 删除最小节点
     */
    public void deleteMin(){
        if(root == null) throw  new NoSuchElementException("根节点为空");

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);

        if(root != null) root.color = BLACK;
    }

    private Node deleteMin(Node h){
        if(h.left == null)
            return null;
        if(!isRed(h.left) && !isRed(h.left.left)){
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * 插入
     * @param key
     * @param val
     */
    public void put(Key key, Val val){
        root = put(root, key ,val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Val val){
        if(h == null)
            return new Node(key, val, 1, RED);
        int cmp = h.key.compareTo(key);
        if(cmp < 0) h.right = put(h.right , key , val);
        else if(cmp > 0) h.left = put(h.left, key, val);
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }


    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }


    private boolean isRed(Node x){
        if(x == null)
            return false;
        return x.color;
    }

    private class Node{
        Key key;
        Val val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Val val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }


    }

}
