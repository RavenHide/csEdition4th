package algsCS.string.substring;

import java.math.BigInteger;
import java.util.Random;

/**
 * 利用散列值来查找子字符串的算法
 */
public class RabinKarp {
    private String pat;//拉斯维加斯算法需要匹配hash值相同的匹配串
    private long patHash;
    private int M;
    private long Q; //蒙特卡洛算法需要将Q 取 大于 10 的20 次方 ，几乎没有hash碰撞的出现
    private int R = 256;
    private long RM; //

    public RabinKarp(String pat) {
        this.pat = pat;
        M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private long hash(String pat, int m) {
        long h = 0;
        for (int i = 0; i < m; i++) {
            h = (h * R + pat.charAt(i)) % Q;
        }
        return h;
    }

    /**
     * 校验是否正确匹配，拉斯维加斯算法需要将hashvalue相同的模式串和文本子串进行一次匹配校验
     * 蒙特卡洛算法则不需要
     * @param i
     * @return
     */
    public boolean check(int i){
        return true;
    }

    private int search(String txt){
        int N = txt.length();
        long txtHash = hash(txt, M);
        if(patHash == txtHash) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(txtHash == patHash){
                if(check(i - M + 1)) return i + M + 1;
            }
        }
        return N;
    }

    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
