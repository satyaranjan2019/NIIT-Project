package com.niit.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.CategoryDao;
import com.niit.model.Category;

@Repository("categoryDao")
@Transactional
@SuppressWarnings("unchecked")
public class CategoryDaoImpl implements CategoryDao  
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	
	}

	public List<Category> getAllCategory() {
		Session session = getSession();
		Query query=session.createQuery("from Category");
		List<Category> list = query.list();
		return list;
	}

	public boolean updateCategory(Category category) 
	{
		try {
			Session session=getSession();
			session.update(category);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e) 
		{
			return false;
		}
	}

	public boolean deleteCategoryById(int id) {
		try
		{
			Session session=getSession();
			session.delete(id);
			session.flush();
			session.close();
			return true ;
		}
		catch(HibernateException e)
		{
		return false;
		}
	}

	public Category getCategoryById(int id) {
		Session session = getSession();
		Query query=session.createQuery("FROM Category c where categoryId=:categoryId");
		query.setParameter("categoryId",id);
		Category c=(Category)query.uniqueResult();
		return c;
	}

	public boolean addCategory(Category category) {
		try
		{
			Session session=getSession();
			session.save(category);
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
