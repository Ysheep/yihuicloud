package com.qzdsoft.vo;

/**
 * 服务器返回信息
 * @author pc-20170420
 *
 */
public enum ResultEnum {
	
	SUCCESS(200,"成功"),
	ERROR(500,"服务器内部错误")
	;
	
	private Integer code;//返回code
	private String message;//返回message
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	ResultEnum(Integer code,String message){
		this.code = code;
		this.message = message;
	}
}
