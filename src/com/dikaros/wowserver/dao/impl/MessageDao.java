package com.dikaros.wowserver.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dikaros.wowserver.bean.Message;
import com.dikaros.wowserver.dao.IMessageDao;
import com.dikaros.wowserver.util.HibernateSessionFactory;

public class MessageDao implements IMessageDao{

	@Override
	public boolean save(Message message) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(message);
			tx.commit();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
			return false;
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return true;
	}

	
//
//
//	@Override
//	public boolean exist(User user) {
//		@SuppressWarnings("unused")
//		Session session = HibernateSessionFactory.getSession();
//		
//		return false;
//	}
//
//	@Override
//	public User query(long uid) {
//		Session session = HibernateSessionFactory.getSession();
//		User user = (User) session.get(User.class, uid);
//		session.close();
//		return user;
//	}
//
//	@Override
//	public User query(String phone) {
//		Session session = HibernateSessionFactory.getSession();
//		System.err.println(phone+"--"+session);
//		Query query = session.createQuery("FROM User WHERE phone=:phone");
//		query.setParameter("phone", phone);
//		if (query.list().size()>0) {
//			return (User) query.list().get(0);
//		}else {
//			return null;
//		}
//	}
	
	@Override
	public boolean update(Message message) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.update(message);
			session.beginTransaction().commit();
			
		} catch (Exception e) {
			return false;
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
		return true;
	}

	@Override
	public boolean delete(Message message) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.delete(message);
			session.beginTransaction().commit();
			
		} catch (Exception e) {
			return false;
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> query(long senderId, long receiverId) {
		Session session = HibernateSessionFactory.getSession();
//		System.err.println(phone+"--"+session);
		Query query = session.createQuery("FROM User WHERE senderid=:senderid and receiverid=:receiverid");
		query.setParameter("senderid", senderId);
		query.setParameter("receiverid", receiverId);
		if (query.list().size()>0) {
			return query.list();
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> query(long senderId, long receiverId, long startTime,
			long endTime) {
		Session session = HibernateSessionFactory.getSession();
//		System.err.println(phone+"--"+session);
		Query query = session.createQuery("FROM User WHERE senderid=? and receiverid=? and time>=? and time <=?");
		query.setParameter(0, senderId);
		query.setParameter(1, receiverId);
		query.setParameter(2, startTime);
		query.setParameter(3, endTime);
		if (query.list().size()>0) {
			return query.list();
		}else {
			return null;
		}
	}
	
}
