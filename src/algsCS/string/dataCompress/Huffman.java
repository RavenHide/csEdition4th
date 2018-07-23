package algsCS.string.dataCompress;

import algsCS.sort.priorityQueue.HeapMinPQ;

public class Huffman {
    private static int R = 256;

    public static void compress(){
        String s = "Huffman";//读取输入流
        char[] input = s.toCharArray();
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        //构造Huffman编码树
        Node root = buildTrie(freq);
        //构造编译表
        String[]  st = new String[R];
        buildCode(st, root, "");
        //打印解码用的单词查找树
        writeTrie(root);
        //打印字符总数
        System.out.println(input.length);
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if(code.charAt(j) == '1') {
                    //输出1
                }else{
                    //输出0
                }
            }
        }
    }

    /**
     * 读取Huffman树
     * @return
     */
    private static Node readTrie(){
        //如果读到流是个1 return new Node(读取一个字符, 0, null, null)
        //如果是个0，那么就是个父节点
        return new Node('\0', 0, readTrie(), readTrie());
    }

    private static void writeTrie(Node x){
        if(x.isLeaf()){
            //输出一个1，写到输出流
            System.out.println(x.ch);
            return;
        }
        //输出一个0
        writeTrie(x.left);
        writeTrie(x.right);
    }

    /**
     * 构造编译表
     * @param st
     * @param x
     * @param s
     */
    private static void buildCode(String[] st, Node x, String s) {
        if(x.isLeaf()){
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    /**
     * 构造huffman编码树
     * @param freq
     * @return
     */
    private static Node buildTrie(int[] freq){
        HeapMinPQ<Node> pq = new HeapMinPQ<>();
        for (char c = 0; c < R; c++) {
            if(freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null));
        }
        while(pq.size() > 1){
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }


    private static class Node implements Comparable<Node>{
        private char ch;
        private int freq;
        private final Node left, right;

        private Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return this.left == null && this.right == null;
        }


        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }
}
