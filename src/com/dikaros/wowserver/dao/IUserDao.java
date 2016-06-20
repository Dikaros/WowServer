package com.dikaros.wowserver.dao;

import com.dikaros.wowserver.bean.User;


public interface IUserDao {
	/**
	 * @param user
	 */
	public boolean save(User user);
	
	/**
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * @param user
	 * @return
	 */
	public boolean remove(User user);
	
	/**
	 * @param user
	 * @return
	 */
	public boolean exist(User user);
	
	/**
	 * @param uid
	 * @return
	 */
	public User query(long uid);
	
	/**
	 * @param phone
	 * @return
	 */
	public User query(String phone);
}
