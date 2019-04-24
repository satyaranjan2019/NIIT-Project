package com.niit.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.DAO.CartDao;
import com.niit.DAO.CartItemsDao;
import com.niit.DAO.CategoryDao;
import com.niit.DAO.UserDao;
import com.niit.model.Cart;
import com.niit.model.CartItems;
import com.niit.model.Category;
import com.niit.model.User;

@Controller
public class UserController {

	@Autowired 
	  UserDao userDao;
	@Autowired 
	  CartDao cartDao;
	@Autowired 
	  CartItemsDao cartItemsDao;
	@Autowired 
	  CategoryDao categoryDao;
	
	
	
	
	@RequestMapping("/Login")
	public String Login(@RequestParam(required=false) String error,@RequestParam(required=false) String logout,Model model)
	{
		if(error!=null)
		model.addAttribute("error","Invalid Username/Password");
		if(logout!=null)
			model.addAttribute("msg","Loggedout Successfully");
		return "Login";
		
		 
	}
	// adding user to database by sign up process
	@RequestMapping("/AddUser")
	public ModelAndView showform() {
		return new ModelAndView("AddUser", "command", new User());
	}
	
	@RequestMapping(value="/saveUser",method = RequestMethod.POST)  
    public String save(@ModelAttribute("User") User user , HttpServletRequest request,Model model) {

		boolean b = userDao.checkUserId(user.getUserId());
		if (b == true) {
			
			Cart c=new Cart();
			c.setCartDesc(user.getUserId()+ "'s Cart");
			cartDao.addCart(c);
			user.setCart(c);
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			userDao.addUsers(user);
			return "redirect:/test";

			
		}
		
		else {
			
			model.addAttribute("command", user);
			model.addAttribute("errorMessage", "User Id already exists. Please try again!!");
			return "AddUser";
			
		}
	}
	// displaying user list 
	@RequestMapping("/DisplayUser")
    public ModelAndView getAllUsers(){
    	List<User> list=userDao.getAllUser();
    	return new ModelAndView("DisplayUser","list",list);
	}
	
	// deleting user from database 
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String id) {
		System.out.println("delete is called :="+ id);
		userDao.deleteUser(id);
		System.out.println("yo");
		return new ModelAndView("redirect:/DisplayUser");
	}  
	
	
	
	
	
	// editing user details 

    @RequestMapping(value="/EditUser/{id}")  
    public ModelAndView editusers(@PathVariable String id)
    {  
        User users=userDao.getUserById(id);  
        System.out.println("n "+users.getUserId());
        System.out.println("aaaaa");
        return new ModelAndView("EditUser","command",users);
        
    } 
    
    @RequestMapping(value="/editsaveUser",method = RequestMethod.POST)  
    public ModelAndView  editsaveusers(@ModelAttribute("User") User users , HttpServletRequest request,Model model)
    {  System.out.println("bbbbbbb");
    	userDao.updateUser(users);
    	users.setRole("ROLE_USER");
		users.setEnabled(true);
		userDao.updateUser(users);
        return new ModelAndView("redirect:/test");  
    }   
    @RequestMapping("/test")
	public String test(HttpSession session, Principal userPrincipal, Model model) {

		if(userPrincipal!=null)
		{
		String userId = userPrincipal.getName();
		User u = userDao.getUserById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		model.addAttribute("cartSize", citem.size());
		}
		List<Category> list = categoryDao.getAllCategory();
		session.setAttribute("categoryList", list);
		model.addAttribute("categoryList", list);
		
		return "test";
	
	}
    @RequestMapping(value="/myProfile/{id}")  
    public ModelAndView viewUser(@PathVariable String id,HttpSession session){  
         User user=userDao.getUserById(id);
         session.setAttribute("userDescription", user);
        return new ModelAndView("myProfile","userDescription",user);  
    } 
}
