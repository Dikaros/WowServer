package com.dikaros.wowserver.dao;

import com.dikaros.wowserver.bean.User;


public interface IUserDao {
	/**
	 * �����û�
	 * @param user
	 */
	public boolean save(User user);
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	
	/**
	 * ɾ���û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean remove(User user);
	
	/**
	 * ��ѯ�û��Ƿ����
	 * @param user
	 * @return
	 */
	public boolean exist(User user);
	
	/**
	 * ��uid��ѯ�û�
	 * @param uid
	 * @return
	 */
	public User query(long uid);
	
	/**
	 * �Ե绰�����ѯ�û�
	 * @param phone
	 * @return
	 */
	public User query(String phone);
}
