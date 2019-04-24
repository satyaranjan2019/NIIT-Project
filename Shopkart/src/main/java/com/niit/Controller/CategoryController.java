
package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.DAO.CategoryDao;
import com.niit.model.Category;


@Controller
public class CategoryController 
{
	 @SuppressWarnings("unused")
	private static final Category category = null;
	@Autowired 
	  CategoryDao categoryDao;
	 @RequestMapping("/CategoryDisplay")  
	    public ModelAndView index(){  
	        return new ModelAndView("CategoryDisplay"); 
	    }    //return new ModelAndView("index"); here we are calling index to view the category and products available ...
	 @RequestMapping("/viewcategory")
	    public ModelAndView getAllCategory(){
	    	List<Category> list=categoryDao.getAllCategory();
	    	return new ModelAndView("viewcategory","list",list);
	    	
	    }  //return new ModelAndView("viewcategory"); here we are gathering information of products based on categories ..... 
	 
	@RequestMapping(value="/editCategory/{id}")  
	    public ModelAndView edit(@PathVariable int id)
	    {  
	        Category category=categoryDao.getCategoryById(id);  
	        System.out.println("n "+category.getCategoryName());
	        return new ModelAndView("editCategory","command",category);  
	    } //return new ModelAndView("editCategory"); here we are editing the category for various updates regarding products ...
	    
	    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
	    public ModelAndView editsave(@ModelAttribute("category") Category category)
	    {  
	    	categoryDao.updateCategory(category);
	        return new ModelAndView("redirect:/viewcategory");  
	    } //return new ModelAndView("viewcategory") here we are saving the updates of cateogory after editing with various updates ....
	     
		@RequestMapping("/addCategory")  
	    public ModelAndView showform(){  
	        return new ModelAndView("addCategory","command",new Category());  
	    } 
		 @RequestMapping(value="/save",method = RequestMethod.POST)  
		    public ModelAndView save(@ModelAttribute("category") Category category)
		 {  
		    	categoryDao.addCategory(category);
		        return new ModelAndView("redirect:/viewcategory");
		    }  //return new ModelAndView("viewcategory") here we are adding a new platform for category for product differentiation ..
		 @RequestMapping(value="/deleteCategory/{id}",method = RequestMethod.GET)  
		    public ModelAndView delete(@PathVariable int id){ 
		    	System.out.println("delete is called");
		    	categoryDao.deleteCategoryById(id);
		        return new ModelAndView("redirect:/viewcategory");  
		    }  
}
