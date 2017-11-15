package com.zyl.demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zyl
 *错误发送辅助类
 */
public class Result {
	private String tourl;//跳转url
	private String msg = null;//返回的消息 success or error
	private int code;//状态码
	
	public static Map<String, Object> toUrl(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tourl", url);
		return map;
	}
	
	public Result() {
	}
	
	public Result(String tourl) {
		this.tourl=tourl;
		
	}
	public Result (String tourl,int code,String msg){
		this.tourl=tourl;
		this.code=code;
		this.msg=msg;
	}

	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}

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
	
}
