package com.stevenw.standards;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/12/2
 */
public class MapExample {
    final static Map<String,Integer> map = new HashMap();

    static {
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
    }

    /**
     * 需要 Map 的主键和取值时，应该迭代 entrySet
     * 当循环中只需要 Map 的主键时，迭代 keySet 是正确的。
     * 但是，当需要主键和取值时，迭代 entrySet 才是更高效的做法，比先迭代 keySet 后再去 get 取值性能更佳。
     */
    public static void iteration(){
        //只迭代key
        for (String key:map.keySet()) {
            System.out.println("key为："+key);
        }
        //迭代value
        for (Map.Entry<String,Integer> entry:map.entrySet()) {
          String key =  entry.getKey();
          Integer value = entry.getValue();
        }
    }
}
