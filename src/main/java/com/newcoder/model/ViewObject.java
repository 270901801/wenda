package com.newcoder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hsc
 * @date 2019/7/31 - 18:05
 */
public class ViewObject {
    private Map<String,Object> objs=new HashMap<String,Object>();
    public void set(String key,Object value){
        objs.put(key,value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
