package algsCS.string.search;

import algsCS.basic.Queue;

/**
 * 基于单词查找树的符号表
 */
public class TrieST<Value> {
    public static final int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (key.length() == d) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;

    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<>();
        Node x = get(root, pre, 0);
        collect(x, pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char i = 0; i < R; i++) {
            collect(x.next[i], pre + i, q);
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<>();
        collect(root, "", pat, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;
        char nextChar = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (nextChar == '.' || nextChar == c)
                collect(x.next[c], pre + c, pat, q);
        }
    }

    public String longsPrefixOf(String s) {
        int length = search(root, s, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d) {
        if (x == null) return d - 1;
        if (d == s.length()) return d;
        char c = s.charAt(d);
        return search(x.next[d], s, d + 1);
    }

    public void delete(String key){
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if(x == null) return null;
        if(d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if(x.val != null) return x;
        for (char c = 0;  c < R; c++) {
            if(x.next[c] != null) return x;
        }
        return null;
    }
}
