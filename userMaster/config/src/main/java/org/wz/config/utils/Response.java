package org.wz.config.utils;

import org.wz.config.bean.Page;

import java.util.HashMap;

/**
 * @project: userMaster
 * @package: org.wz.config.utils
 * @author: Administrator
 * @crDate: 2019/3/24 14:38
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class Response extends HashMap<String,Object> {

    public Response(){
        put("code",0);
        put("msg","ok");
    }

    public static Response ok(String msg){
        Response response = new Response();
        response.put("msg",msg);
        return response;
    }
    public static Response ok() {
        return new Response();
    }

    public static Response error(int code, String msg) {
        Response r = new Response();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Response error() {
        return error (500,"操作失败");
    }

    public static Response operate(boolean b){
        if(b){
            return Response.ok();
        }
        return Response.error();
    }

    public static Response page(Page page){

        return  Response.ok().put("page",page);
    }
    public static Response data(Object data){
        return Response.ok().put("data",data);
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
