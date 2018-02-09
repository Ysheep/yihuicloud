package com.qzdsoft.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifh on 16/8/16.
 */
public class Error implements java.io.Serializable {
    private String code;
    private Object msg;

    public Error() {
    }

    public Error(String code, Object msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
}

