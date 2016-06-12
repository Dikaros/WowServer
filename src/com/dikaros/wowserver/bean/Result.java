package com.dikaros.wowserver.bean;

import com.google.gson.Gson;

public class Result {
	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result() {
		// TODO 自动生成的构造函数存根
	}
	int code;
	String message;
	
	
	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code 要设置的 code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message 要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this, getClass());
	}
	
}
