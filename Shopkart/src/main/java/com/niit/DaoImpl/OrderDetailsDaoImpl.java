package com.niit.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.OrderDetailsDao;
import com.niit.model.OrderDetails;
import com.niit.model.User;

@Repository("orderDetailsDao")
@Transactional
@SuppressWarnings("unchecked")
public class OrderDetailsDaoImpl implements OrderDetailsDao 
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetails) 
        {
		Session session = getSession();
		Query query=session.createQuery("FROM OrderDetails u where u.OrderDetailsId=:orderDetailsId");
		query.setParameter("orderDetailsId", orderDetails);
		OrderDetails u=(OrderDetails)query.uniqueResult();
		return u;
	}	

	public boolean updateOrderDetails(OrderDetails orderDetails) {
		
        	try
        	{
                Session session = getSession();
		        session.update(orderDetails);
		        session.flush();
		        session.close();
		        return true;
        	}
        	catch(HibernateException e)
        	{
        		return false;
        	}
	}

	public boolean deleteOrderDetails(OrderDetails orderDetails) {
		try
    	{
            Session session = getSession();
	        session.delete(orderDetails);
	        session.flush();
	        session.close();
	        return true;
    	}
    	catch(HibernateException e)
    	{
    		return false;
    	}
	}

	public boolean insertOrderDetails(OrderDetails orderDetails) {
		try
    	{
            Session session = getSession();
	        session.save(orderDetails);
	        session.flush();
	        session.close();
	        return true;
    	}
    	catch(HibernateException e)
    	{
    		return false;
    	}
	}

	public List<OrderDetails> getOrderDetailsListByUser(User user) {
		Session session = getSession();
		Query query=session.createQuery("FROM OrderDetails order where order.user.userId=:userid");
		query.setParameter("userid", user.getUserId());
		List<OrderDetails> order=query.list();
		return order;
	}

	

}
