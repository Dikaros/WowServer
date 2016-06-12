package com.dikaros.wowserver.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import antlr.collections.List;

import com.dikaros.wowserver.bean.User;
import com.dikaros.wowserver.dao.IUserDao;
import com.dikaros.wowserver.util.HibernateSessionFactory;


/**
 * @author Dikaros
 *
 */
public class UserDao implements IUserDao{

	@Override
	public boolean save(User user) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(user);
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

	@Override
	public boolean update(User user) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.update(user);
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
	public boolean remove(User user) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.delete(user);
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
	public boolean exist(User user) {
		@SuppressWarnings("unused")
		Session session = HibernateSessionFactory.getSession();
		
		return false;
	}

	@Override
	public User query(long uid) {
		Session session = HibernateSessionFactory.getSession();
		User user = (User) session.get(User.class, uid);
		session.close();
		return user;
	}

	@Override
	public User query(String phone) {
		Session session = HibernateSessionFactory.getSession();
//		System.err.println(phone+"--"+session);
		Query query = session.createQuery("FROM User WHERE phone=:phone");
		query.setParameter("phone", phone);
		if (query.list().size()>0) {
			return (User) query.list().get(0);
		}else {
			return null;
		}
	}
	
//	public ArrayList<User> getFriend(String userId) {
//		
//	}


}
