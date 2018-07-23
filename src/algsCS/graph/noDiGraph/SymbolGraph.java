package algsCS.graph.noDiGraph;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 符号图
 */
public class SymbolGraph {
    private Map<String, Integer> map; //符号表
    private String[] keys; //反索引数组
    private Graph G;

    public SymbolGraph(InputStream is, String sp) throws IOException {
        map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null){
            String[] a = line.split(sp);
            for (int i = 0; i < a.length; i++) {
                if(!map.containsKey(a[i])){
                    map.put(a[i], map.size());
                }
            }
        }
        keys = new String[map.size()];

        for (Map.Entry<String, Integer> s : map.entrySet()) {
            keys[s.getValue()] = s.getKey();
        }

        G = new Graph(map.size());

        BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
         line = null;
        while ((line = br.readLine()) != null){
            String[] a = line.split(sp);
            int v = map.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, map.get(a[i]));
            }
        }

    }

    public boolean contains(String s){
        return map.containsKey(s);
    }

    public int index(String s){
        return map.get(s);
    }

    public String name(int v){
        if(v < 0 || v >= keys.length){
            return null;
        }
        return keys[v];
    }
}
