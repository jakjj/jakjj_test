package org.wz.config.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: userManager
 * @package: utils
 * @author: Administrator
 * @crDate: 2019/3/19 16:27
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class MapUtils {
    public static Map<String,String> getMap(Map<String,String[]> map){
        Map<String,String> newMap = new HashMap<>();
        map.forEach((k,v)->{
            newMap.put(k,v[0]);
        });
        return newMap;
    }
}
