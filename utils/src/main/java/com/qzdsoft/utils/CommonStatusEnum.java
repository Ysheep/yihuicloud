package com.qzdsoft.utils;

/**
 * Created by Yang
 */
public enum CommonStatusEnum {

    using(1, "正常"),
    stop(0, "不可用"),
    ;
    private Integer key; 		// 所属类型

    private String val; 		// 类型值

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    CommonStatusEnum(Integer key, String val) {
        this.key = key;
        this.val = val;
    }
}
