package com.dikaros.wowserver.bean;

import com.google.gson.Gson;

public class FriendView {
	long hostId;
	long friendId;
	private String friendName;
	private String friendMark;
	private String friendPhone;
	private String friendGender;
	
	private String friendBirthday;
	
	private String friendMessage;
	
	private int friendRank;
	/**
	 * @return hostId
	 */
	public long getHostId() {
		return hostId;
	}
	/**
	 * @param hostId 要设置的 hostId
	 */
	public void setHostId(long hostId) {
		this.hostId = hostId;
	}
	/**
	 * @return friendId
	 */
	public long getFriendId() {
		return friendId;
	}
	/**
	 * @param friendId 要设置的 friendId
	 */
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	/**
	 * @return friendName
	 */
	public String getFriendName() {
		return friendName;
	}
	/**
	 * @param friendName 要设置的 friendName
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	/**
	 * @return friendMark
	 */
	public String getFriendMark() {
		return friendMark;
	}
	/**
	 * @param friendMark 要设置的 friendMark
	 */
	public void setFriendMark(String friendMark) {
		this.friendMark = friendMark;
	}
	/**
	 * @return friendPhone
	 */
	public String getFriendPhone() {
		return friendPhone;
	}
	/**
	 * @param friendPhone 要设置的 friendPhone
	 */
	public void setFriendPhone(String friendPhone) {
		this.friendPhone = friendPhone;
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	/**
	 * @return friendGender
	 */
	public String getFriendGender() {
		return friendGender;
	}
	/**
	 * @param friendGender 要设置的 friendGender
	 */
	public void setFriendGender(String friendGender) {
		this.friendGender = friendGender;
	}
	/**
	 * @return friendBirthday
	 */
	public String getFriendBirthday() {
		return friendBirthday;
	}
	/**
	 * @param friendBirthday 要设置的 friendBirthday
	 */
	public void setFriendBirthday(String friendBirthday) {
		this.friendBirthday = friendBirthday;
	}
	/**
	 * @return friendMessage
	 */
	public String getFriendMessage() {
		return friendMessage;
	}
	/**
	 * @param friendMessage 要设置的 friendMessage
	 */
	public void setFriendMessage(String friendMessage) {
		this.friendMessage = friendMessage;
	}
	/**
	 * @return friendRank
	 */
	public int getFriendRank() {
		return friendRank;
	}
	/**
	 * @param friendRank 要设置的 friendRank
	 */
	public void setFriendRank(int friendRank) {
		this.friendRank = friendRank;
	}

	
	
//	String
}
