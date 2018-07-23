package algsCS.search;

public class BTree<Key extends Comparable<Key>, Value> {
    private static final int M = 4; //max children per B-tree node = M-1;

    private Node root;
    private int height; // height of th B-tree;
    private int n;//number of key-value pairs in the B-tree

    private static class Node {
        private int m;
        private Entry[] children = new Entry[M];

        public Node(int m) {
            this.m = m;
        }
    }

    private static class Entry {
        private Comparable key;
        private final Object val;
        private Node next;

        private Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }


    public BTree() {
        root = new Node(0);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public int height() {
        return height;
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    private Value search(Node x, Key key, int height) {
        Entry[] children = x.children;
        //外部节点
        if (height == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, (Key) children[j].key)) return (Value) children[j].val;
            }
        } else {//内部节点
            for (int i = 0; i < x.m; i++) {

                if (i + 1 == x.m || less(key, (Key) children[i + 1].key))//0索引位置是哨兵键，比所有的键都小，所以直接从i+1开始比较
                    return search(children[i].next, key, height - 1);
            }
        }

        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        Node u = insert(root, key, val, height);
        n++;
        if (u == null) return;

        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Value val, int height) {
        int j = 0;
        Entry t = new Entry(key, val, null);

        if (height == 0) {//叶子节点
            for (j = 0; j < h.m; j++) {
                if (less(key, (Key) h.children[j].key)) break;
            }
        } else {
            for (j = 0; j < h.m; j++) {
                if (j + 1 == h.m || less(key, (Key) h.children[j + 1].key)) {
                    Node n = insert(h.children[j++].next, key, val, height - 1);
                    if(n == null) return null;
                    t.key = n.children[0].key;
                    t.next = n;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) return null;
        else return split(h);
    }

    /**
     * 把一个node分裂成两个
     *
     * @param h
     * @return
     */
    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++) {
            t.children[j] = h.children[j + M / 2];
        }
        return t;
    }

    private boolean less(Key k1, Key k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Key k1, Key k2) {
        return k1.compareTo(k2) == 0;
    }
}
