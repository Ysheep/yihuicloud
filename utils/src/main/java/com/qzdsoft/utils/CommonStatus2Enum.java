package com.qzdsoft.utils;

/**
 * Created by Yang
 */
public enum CommonStatus2Enum {

    zc(0, "正常"),
    dj(1, "冻结"),
    sc(2, "删除"),
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

    CommonStatus2Enum(Integer key, String val) {
        this.key = key;
        this.val = val;
    }
}
