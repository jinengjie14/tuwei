package com.zyl.demo.utils;

import com.zyl.demo.model.User;

/**
 * @author Zyl
 *接收远程状态参数
 */
public class LoginResponse {
	private String msg;//消息
	private int code;//状态码
	private User data;//接收的user参数 id and account
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public User getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
}
