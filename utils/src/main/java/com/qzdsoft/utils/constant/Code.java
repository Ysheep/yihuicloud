package com.qzdsoft.utils.constant;

import com.qzdsoft.utils.Error;
/**
 * Created by lifh on 16/12/14.
 */
public enum Code {
    success("1000", "成功"),
    login_user_not_exists_error("1001", "用户不存在"),
    login_password_error("1002", "登录密码错误"),
    system_error("9999", "系统迷路了");

    private String code;
    private String msg;

    private Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public Error error() {
        return new Error(this.code(), this.msg());
    }

}
