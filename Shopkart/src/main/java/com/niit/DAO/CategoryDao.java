package com.niit.DAO;

import java.util.List;

import com.niit.model.Category;

public interface CategoryDao 
{
	public List<Category> getAllCategory();
	public boolean updateCategory(Category category);
    public boolean deleteCategoryById(int id);
	public Category getCategoryById(int id);
	public boolean addCategory(Category category);
}
