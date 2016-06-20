package com.dikaros.wowserver.dao;

import java.util.List;

import com.dikaros.wowserver.bean.Friend;
import com.dikaros.wowserver.bean.FriendView;

public interface IFriendDao {
	/**
	 * @param friend
	 */
	public boolean save(Friend friend);
	
	/**
	 * @param friend
	 * @return
	 */
	public boolean update(Friend friend);
	
	/**
	 * @param friend
	 * @return
	 */
	public boolean remove(Friend friend);
	
	/**
	 * @param friend
	 * @return
	 */
	public boolean exist(Friend friend);
	
	
	/**
	 * 查询一个用户的朋友
	 * @param hostId
	 * @return
	 */
	public List<FriendView> queryFriend(long hostId);
}
