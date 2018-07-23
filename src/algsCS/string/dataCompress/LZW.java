package algsCS.string.dataCompress;

import algsCS.string.search.TST;

public class LZW {
    private static final int R = 256;//输入字符数
    private static final int L = 4096; //编码总数2^12
    private static final int W = 12; //编码宽度

    private static int[] flow = new int[5];// 测试使用

    public static void compress(){
        String input = "ABABABA";
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + (char)i, i);
        }
        int code = R + 1;
        int i = 0;
        while(input.length() > 0){
            String s = st.longestPrefixOf(input);
            System.out.println(st.get(s) + " -> " + W);

            flow[i++] = st.get(s);

            int t = s.length();
            if(t < input.length() && code < L)
                st.put(input.substring(0, t + 1), code++);

            input = input.substring(t);
        }
        flow[i] = R;
        System.out.println(R + " -> " + W);
    }

    public static void expand(){
        String st[] = new String[L];

        int i;
        for (i = 0; i < R; i++) {
            st[i] = "" + (char)i;
        }
        st[i++]=" ";
        int j = 0;//测试使用
        int codeword = flow[j++];//测试使用
        String val = st[codeword];
        while(true){
            System.out.println(val);
            codeword = flow[j++];//测试使用
            if(codeword == R) break;

            String s = st[codeword];
            if(i == codeword)
                s = val + val.charAt(0);
            if(i < L)
                st[i++] = val + s.charAt(0);
            val = s;
        }
    }

    public static void main(String[] args){
        compress();
        expand();
    }
}
