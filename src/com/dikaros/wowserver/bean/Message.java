package com.dikaros.wowserver.bean;


import com.google.gson.Gson;


public class Message {
	
	public Message(long senderId, long receiverId, long time, String msg,int type) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.time = time;
		this.msg = msg;
		this.type = type;
	}
	
	public Message() {
	}
	
	long id;
	long senderId;
	long receiverId;
	long time;
	String msg;
	int type;
	String httpSessionId;
	/**
	 * @return senderId
	 */
	public long getSenderId() {
		return senderId;
	}
	/**
	 * @param senderId
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	/**
	 * @return receiverId
	 */
	public long getReceiverId() {
		return receiverId;
	}
	/**
	 * @param receiverId
	 */
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	/**
	 * @return time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message [senderId=" + senderId + ", receiverId=" + receiverId
				+ ", time=" + time + ", msg=" + msg + "]";
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this, getClass());
		
	}

	/**
	 * @return httpSessionId
	 */
	public String getHttpSessionId() {
		return httpSessionId;
	}

	/**
	 * @param httpSessionId 要设置的 httpSessionId
	 */
	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}


}
