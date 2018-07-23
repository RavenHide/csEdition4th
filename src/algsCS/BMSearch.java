package algsCS;

public class BMSearch {
    public int indexOf(String str, String subStr) {
        if (str.length() < subStr.length()) {
            return -1;
        }

        int j = subStr.length() - 1; //模式串长度
        int i = j;
        while (j > -1 || i >= str.length()) {
            if (subStr.charAt(i) == str.charAt(j)) {
                j--;
                i--;
            } else {
                //好后缀
//                //坏字符规则
//                int k = j - 1;
//                while(k > -1) {
//                    if (str.charAt(i) == subStr.charAt(k)) {
//                        break;
//                    }
//                    k--;
//                }
//                i = i + (j - k);
//                j = subStr.length();//初始化
            }
        }
        return -1;
    }


    /**
     * 好字符规则
     * @param sIndex 当前str遍历的char的index
     * @param subIndex 当前模式串遍历的char的index
     * @param str 当前主串
     * @param subStr 当前模式串
     * @return
     */
    public int badCharacter(int sIndex, int subIndex, String str, String subStr){
        int k = subIndex - 1;
        while(k > -1) {
            if (str.charAt(sIndex) == subStr.charAt(k)) {
                break;
            }
            k--;
        }
        return sIndex + (subIndex - k);
    }
}
