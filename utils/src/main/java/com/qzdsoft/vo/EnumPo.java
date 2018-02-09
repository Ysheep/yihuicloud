package com.qzdsoft.vo;

import java.io.Serializable;

/**
 * Created by Yang
 */
public class EnumPo implements Serializable {

    private Object key;

    private Object val;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public EnumPo() {
        super();
    }

    public EnumPo(Object key, Object val) {
        super();
        this.key = key;
        this.val = val;
    }

}
