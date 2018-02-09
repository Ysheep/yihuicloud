package com.qzdsoft.utils;

/**
 * Created by Yang
 */
public enum CommonSfEnum {

    yes(1, "是"),
    no(0, "否"),
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

    CommonSfEnum(Integer key, String val) {
        this.key = key;
        this.val = val;
    }
}
