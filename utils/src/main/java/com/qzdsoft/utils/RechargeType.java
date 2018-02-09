package com.qzdsoft.utils;

public enum RechargeType {
    
    wechat(0, "微信"),
    alipay(1, "支付宝"),
    unline(2, "线下支付"),
    ;
    private Integer key;        // 所属类型

    private String val;         // 类型值

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

    RechargeType(Integer key, String val) {
        this.key = key;
        this.val = val;
    }

}
