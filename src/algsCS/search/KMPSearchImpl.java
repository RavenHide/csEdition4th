package algsCS.search;

import java.util.Optional;

public class KMPSearchImpl implements ISearch {


    public KMPSearchImpl() {

    }

    @Override
    public int indexOf(String str, String subStr) {
        if(str.length() < subStr.length()){
            return -1;
        }
        int j = 0;
        int i = 0;
        int strLength = str.length();
        int subLength = subStr.length();
        int next[] = initNextArray(subStr);
        while(j < subLength && i < strLength){
            if(j == -1 || str.charAt(i) == subStr.charAt(j)){
                j++;
                i++;

            }else{
                j = next[j];
            }
        }

        if(j == subLength){
            return i - j;
        }
        return -1;
    }

    private int[] initNextArray(String p){
        int next[] = new int[p.length()];
        Optional<String> o = Optional.ofNullable(p);
        p = o.orElse("");
        next[0] = -1;
        int length = p.length();

        int k = -1;//表示前缀
        int j = 0;//表示后缀
        while(j < length - 1){
            if(k == -1 || p.charAt(k) == p.charAt(j)){
                k++;
                j++;

                if(p.charAt(k) != p.charAt(j)){
                    next[j] = k;
                }else{
                    next[j] = next[k];
                }
            }else{
                k = next[k];
            }
        }
        return next;
    }
}
