package com.niit.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.ProductDao;
import com.niit.model.Product;

@Repository("productDao")
@Transactional
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public List<Product> getAllProducts() 
	{
		Session session = getSession();
		Query query=session.createQuery("FROM Product c where  c.productStatus = ? ");
		
		query.setParameter(0, true);
		List<Product> list = query.list();
		session.close();
		return list;
	}

	public Product getProductById(int id) 
	{
		Session session = getSession();
		Query query=session.createQuery("FROM Product prod where productId=:productId");
		query.setParameter("productId", id);
		Product prod=(Product)query.uniqueResult();
		return prod;
	}

	public boolean saveOrUpdateProduct(Product product) 
	{
		try
		{
			Session session=getSession();
			session.update(product);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e) 
		{
			return false;
		}
		
	}

	public List<Product> listByCategoryId(int categoryId) 
	{
		Session session = getSession();
		Query query=session.createQuery("FROM Product c where c.productCategory.categoryId= ? and c.productStatus = ? ");
		query.setParameter(0, categoryId);
		query.setParameter(1, true);
		List<Product> c=query.list();
		session.close();
		return c;
	}

	public boolean deleteProduct(int id) 
	{
		Session session=getSession();
		try
		{
		
		Product p=getProductById(id);
		if (p!=null)
		{
			p.setProductStatus(false);
			session.update(p);
			session.flush();
			session.close();
			return true ;
		}
		else
		{
			session.close();
			return false ;
		}
		
		
	}
	catch(HibernateException e)
	{
		session.close();
	return false;
	}
	}

	public boolean updateProduct(Product product) 
	{
		try {
			Session session=getSession();
			session.update(product);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e) 
		{
			return false;
		}
	}

	public boolean addProduct(Product product) 
	{
	
		try
		{
			Session session=getSession();
			session.save(product);
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
