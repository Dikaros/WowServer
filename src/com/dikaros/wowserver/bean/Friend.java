package com.dikaros.wowserver.bean;

import java.io.Serializable;


public class Friend implements Serializable {
	long hostId;
	long friendId;
	String friendMark;
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
	
	public Friend reverse(){
		Friend friend = new Friend();
		friend.hostId=friendId;
		friend.friendId = hostId;
		return friend;
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


	
}
