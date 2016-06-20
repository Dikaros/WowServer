package com.dikaros.wowserver.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;









import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dikaros.wowserver.bean.Friend;
import com.dikaros.wowserver.bean.FriendView;
import com.dikaros.wowserver.bean.User;
import com.dikaros.wowserver.dao.IFriendDao;
import com.dikaros.wowserver.util.HibernateSessionFactory;

public class FriendDao implements IFriendDao{

	@Override
	public boolean save(Friend friend) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(friend);
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
	public boolean update(Friend friend) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.update(friend);
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
	public boolean remove(Friend friend) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.delete(friend);
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
	public boolean exist(Friend friend) {
		// TODO 自动生成的方法存根
		return false;
	}


	@Override
	public List<FriendView> queryFriend(long hostId) {
		List<FriendView> friendViews = new ArrayList<>();
		Session session = HibernateSessionFactory.getSession();
		org.hibernate.Query query= session.createSQLQuery("select * from friend_view where host_id="+hostId);
		List<Object[]> objects = query.list();
		for (Object[] objects2 : objects) {
			FriendView friendView = new FriendView();
			friendView.setHostId( ((BigInteger) objects2[0]).longValue());
			friendView.setFriendId(((BigInteger) objects2[1]).longValue());
			friendView.setFriendMark((String) objects2[2]);
			friendView.setFriendName((String) objects2[3]);
			friendView.setFriendPhone((String) objects2[4]);
			friendView.setFriendGender((String) objects2[5]);
			friendView.setFriendBirthday((String) objects2[6]);
			friendView.setFriendMessage((String) objects2[7]);
			friendView.setFriendRank((int) ( objects2[8]));
			friendViews.add(friendView);
		}
		return friendViews;
	}
	

}
