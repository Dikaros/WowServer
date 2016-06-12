package com.dikaros.wowserver.dao;


import java.util.List;

import com.dikaros.wowserver.bean.Message;


public interface IMessageDao {
	public boolean save(Message message);
	public boolean update(Message message);
	public boolean delete(Message message);
	public List<Message> query(long senderId,long receiverId);
	public List<Message> query(long senderId,long receiverId,long startTime,long endTime);
	
}
