package org.wz.config.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: userManager
 * @package: utils
 * @author: Administrator
 * @crDate: 2019/3/20 10:34
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class PageUtils {
    public static Map<String,String> setPage(Map<String,String> map){
        if(map==null){
            map = new HashMap<>();
        }
        int page,limit;
        if (!map.containsKey("page")) {
            map.put("page","1");
            page = 1;
        }else{
            page = Integer.parseInt(map.get("page"));
        }
        if (!map.containsKey("limit")) {
            map.put("limit","10");
            limit = 10;
        }else{
            limit = Integer.parseInt(map.get("limit"));
        }
        int start = (page-1)*limit;
        String end = String.valueOf(page*limit);
        map.put("start",String.valueOf(start));
        map.put("end",end);
        return map;
    }
    public static int getPageCount(Integer count,String limit){
        int lm = 15;
        if (limit == null) {
            return ((count-1)/lm)+1;
        }
        return ((count-1)/Integer.parseInt(limit))+1;
    }
}
