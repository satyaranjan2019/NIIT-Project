package com.niit.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.UserDao;
import com.niit.model.ShippingAddress;
import com.niit.model.User;

@Repository("userDao")
@Transactional
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean addUsers(User user) 
	{
		try
		{
			Session session=getSession();
			session.save(user);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
	}

	
	public List<User> getAllUser() 
	{
		Session session = getSession();
		Query query=session.createQuery("from User");
		List<User> list = query.list();
		return list;
	}

	public User getUserById(String userId) {
		Session session = getSession();
		Query query=session.createQuery("FROM User u where u.userId=:userId ");
		query.setString("userId", userId);
		User u=(User)query.uniqueResult();
		return u;
	}

	public boolean updateUser(User user) 
	{
		try {
			Session session=getSession();
			session.update(user);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e) 
		{
			return false;
		}
	}

	public boolean deleteUser(String userId) 
	{
		try
		{
		Session session=getSession();
		session.delete(userId);
		session.flush();
		session.close();
		return true ;
	}
	catch(HibernateException e)
	{
	return false;
	}
	}

	public boolean checkUserId(String userId)
	{
		Session session= getSession();
		Query query=session.createQuery("from User u where u.userId=:userId");
		query.setParameter("userId", userId);
		User u=(User)query.uniqueResult();
		if(u==null)
		return true;
		else 
		return false;

	}

	public boolean addShippingAddress(ShippingAddress shippingaddress) 
	{
		try
		{
			Session session=getSession();
			session.save(shippingaddress);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
	}

	public ShippingAddress getShippingAddressById(int id) {
		Session session = getSession();
        Query query=session.createQuery("FROM ShippingAddress ship where id=:Id");
        query.setParameter("Id", id);
        ShippingAddress ship=(ShippingAddress)query.uniqueResult();
          return ship;
	}

	public List<ShippingAddress> getShippingAddressByUserId(String userId) {
		Session session = getSession();
		Query query = session.createQuery("from ShippingAddress where user.userId=:userId");
		query.setParameter("userId", userId);
		List<ShippingAddress> shipingAdresList = query.list();
	        return shipingAdresList;
	}

}
