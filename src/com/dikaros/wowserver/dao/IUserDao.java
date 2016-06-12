package com.dikaros.wowserver.dao;

import com.dikaros.wowserver.bean.User;


public interface IUserDao {
	/**
	 * 保存用户
	 * @param user
	 */
	public boolean save(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	public boolean remove(User user);
	
	/**
	 * 查询用户是否存在
	 * @param user
	 * @return
	 */
	public boolean exist(User user);
	
	/**
	 * 以uid查询用户
	 * @param uid
	 * @return
	 */
	public User query(long uid);
	
	/**
	 * 以电话号码查询用户
	 * @param phone
	 * @return
	 */
	public User query(String phone);
}
