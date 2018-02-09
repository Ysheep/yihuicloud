package com.qzdsoft.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author pc-20170420
 * 返回客户端对象
 * @param <T>
 */
public class Response<T> {

	private Integer code;
	private String message;
	private T result;
	
	public Response(Integer code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public Response(Integer code,String message,T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
	/**
	 * 枚举服务器返回信息
	 * @param result
	 */
	public Response(ResultEnum result) {
		this.code = result.getCode();
		this.message = result.getMessage();
	}
	
	/**
     * 枚举服务器返回信息
     * @param result
     */
    public Response(ResultEnum result,T data) {
        this.result = data;
        this.code = result.getCode();
        this.message = result.getMessage();
    }
	
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
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	public static Response<String> success(String message) {
		Response<String> response =  new Response<String>(ResultEnum.SUCCESS);
		if(!StringUtils.isEmpty(message)) {
			response.setMessage(message);
		}
		return response;
	}
	
	public static Response<String> error(String message) {
		Response<String> response =  new Response<String>(ResultEnum.ERROR);
		if(!StringUtils.isEmpty(message)) {
			response.setMessage(message);
		}
		return response;
	}

    @Override
    public String toString()
    {
        return "Response [code=" + code + ", message=" + message + ", result=" + result + "]";
    }
	
}
