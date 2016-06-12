package com.dikaros.wowserver.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="friend")
public class Friend {
	String hostId;
	String friendId;
	/**
	 * @return hostId
	 */
	@Column
	public String getHostId() {
		return hostId;
	}
	/**
	 * @param hostId 要设置的 hostId
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	/**
	 * @return friendId
	 */
	@Column
	public String getFriendId() {
		return friendId;
	}
	/**
	 * @param friendId 要设置的 friendId
	 */
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	
}
