package com.qzdsoft.vo;
/**
 * layui富文本图片上传返回vo
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月27日
 * @see
 * @since 1.0
 * @param <T>
 */
public class LayeditResponse<T> {
	
	private Integer code;//0是成功 其他是失败
	private String msg;
	private T data;
	
	
	
	public LayeditResponse(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	
	public LayeditResponse(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
