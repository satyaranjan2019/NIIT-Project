package com.niit.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.CartItemsDao;
import com.niit.model.CartItems;


@Repository("cartItemsDao")
@Transactional
@SuppressWarnings("unchecked")
public class CartItemsDaoImpl implements CartItemsDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean addCartItems(CartItems cartItems) 
	{
		try
		{
			Session session=getSession();
			session.save(cartItems);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
	}

	public List<CartItems> getAllCartItems() {
		Session session = getSession();
		Query query=session.createQuery("from CartItems");
		List<CartItems> list = query.list();
		session.close();
		return list;
	}

	public boolean UpdateCartItems(CartItems cartItems) 
	{
		try
		{
			Session session=getSession();
			session.update(cartItems);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
		
	}

	public List<CartItems> getCartItemsByCartId(int cartId) {
		Session session = getSession();
        Query query=session.createQuery("FROM CartItems c where c.cart.cartId= ? and status =true");
        query.setParameter(0, cartId);
        List<CartItems> c = query.list();
        session.close();
        return c;
	}

	public boolean deleteCartItems(int cartItemId) 
	{
		try
		{
			Session session = getSession();
			Query query=session.createQuery("FROM CartItems c where cartItemId=:cartItemId");
			query.setParameter("cartItemId", cartItemId);
			CartItems c=(CartItems)query.uniqueResult();
			session.delete(c);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
		
	}

	public List<CartItems> getCartItemsByOrderId(int orderDetailsId) {
		Session session = getSession();
		Query query=session.createQuery("FROM CartItems  where orderDetails.orderDetailsId=:orderDetailsId");
		query.setParameter("orderDetailsId", orderDetailsId);
		List<CartItems> o=query.list();
		session.close();
		return o;
	}

	public CartItems getCartItemsById(int id) {
		Session session = getSession();
	    Query query=session.createQuery("From CartItems where cartItemId=:catid");
	    query.setParameter("catid", id);
	    CartItems car=(CartItems)query.uniqueResult();
	    return car ;
	}
	
}
