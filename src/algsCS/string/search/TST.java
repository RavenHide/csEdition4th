package algsCS.string.search;

import algsCS.basic.Queue;

/**
 * 三向查找树
 */
public class TST<Value> {
    private Node root;

    private class Node {
        private char c;
        private Node left, middle, right;
        Value val;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (key == null || key.isEmpty()) return null;
        char c = key.charAt(d);

        if (c > x.c) return get(x.right, key, d);
        else if (c < x.c) return get(x.left, key, d);
        else if (d < key.length() - 1) return get(x.middle, key, d + 1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);

    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c > x.c) x.right = put(x.right, key, val, d);
        else if (c < x.c) x.left = put(x.left, key, val, d);
        else if (d < key.length() - 1) x.middle = put(x.middle, key, val, d + 1);
        else x.val = val;
        return x;
    }

    public Iterable<String> keysWithPrefix(String pre) {
        if (root == null) return null;
        Node x = get(root, pre, 0);
        Queue<String> q = new Queue<>();
        if (x != null)
            collect(x.middle, pre, q);
        else if (pre == "" || pre.isEmpty()) {
            collect(root, pre, q);
        }
        return q;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre + x.c);
        collect(x.left, pre, q);
        collect(x.middle, pre + x.c, q);
        collect(x.right, pre, q);
    }

    public Iterable<String> keyThatMatch(String pat) {
        Queue<String> q = new Queue<>();
        collect(root, "", pat, q);
        return q;
    }

    public String longestPrefixOf(String str){
        if(str == null || str.isEmpty()) return "";
        int length = search(root, str, 0);
        return str.substring(0, length);
    }

    private int search(Node x, String str, int d) {
        if(x == null) return d;

        char c = str.charAt(d);
        if(c > x.c) return search(x.right, str, d);
        else if(c < x.c) return search(x.left, str, d);
        else if (d < str.length() - 1) return search(x.middle, str, d + 1);
        else return d + 1;
    }

    private void delete(String key){
        Node deleteN = get(root, key, 0);
        if(deleteN == null) return ;
        else delete(deleteN, key, 0);
    }

    private Node delete (Node x, String key, int d){
        if(x == null) return null;

        char c = key.charAt(d);
        if(c > x.c) x.right = delete(x.right, key, d);
        else if(c < x.c)x.left = delete(x.left, key, d);
        else if(d < key.length() - 1)x.middle = delete(x.middle, key, d);
        else x.val = null;

        if(x.val != null || x.middle != null) return x;

        else if(x.left == null) return x.right;
        else if(x.right == null) return x.left;

        return x;


    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        if (x == null) return;
        int d = pre.length();
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if(d == pat.length()) return;

        char c = pat.charAt(d);

        if (c == '.') {
            collect(x.right, pre, pat, q);
            collect(x.left, pre, pat, q);
            collect(x.middle, pre + x.c, pat, q);
        }else {
            if (c > x.c) collect(x.right, pre, pat, q);
            else if (c < x.c) collect(x.left, pre, pat, q);
            else  collect(x.middle, pre + x.c, pat, q);
        }

    }
}
