package com.niit.DaoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.CartDao;
import com.niit.model.Cart;

@Repository("cartDao")
@Transactional

public class CartDaoImpl implements CartDao 
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}
	

	public boolean addCart(Cart cart) 
	{
		try {
			Session session=getSession();
			session.save(cart);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e) 
		{
			return false;
		}
	}

	

}
