package com.niit.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.niit.DAO.OrderDetailsDao;
import com.niit.DAO.ProductDao;
import com.niit.DAO.UserDao;
import com.niit.model.Cart;
import com.niit.model.CartItems;
import com.niit.model.OrderDetails;
import com.niit.model.Product;
import com.niit.model.ShippingAddress;
import com.niit.model.User;

@Controller
public class CartController {

	@Autowired
	CartItemsDao cartItemsDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	OrderDetailsDao orderDetailsDao;
	@Autowired
	CartDao cartDao;

	@RequestMapping(value="/cart/UpdateCartQty", method=RequestMethod.POST)
	public String UpdateCartQty( @RequestParam("id") int id, @RequestParam("qty") int qty  ){
		
		
		
		
		CartItems ci = cartItemsDao.getCartItemsById(id);
		ci.setQty(qty);
		ci.setSubTotal(ci.getProduct().getPrice()*ci.getQty());
		System.out.println("sub tot "+ ci.getSubTotal());
		cartItemsDao.UpdateCartItems(ci);
		
		
		return "redirect:/cart/viewCart";
	}

	@RequestMapping("/cart/viewCart")
	public ModelAndView showCart(Principal principal) {

		int total = 0;
		String userId = principal.getName();
		System.out.println(userId);
		User u = userDao.getUserById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
		System.out.println(cid);
		List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
		System.out.println(list.isEmpty());
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			CartItems citem = (CartItems) itr.next();
			total = total + citem.getSubTotal();
		}
		System.out.println(total);
		ModelAndView mview = new ModelAndView("viewCart");
		mview.addObject("listC", list);
		mview.addObject("total", total);
		return mview;
	}  // here we are retrieving the cart of a particular user and according to user id we are retriving the cart items along with its price 
	
	
	@RequestMapping("/cart/addtocart/{id}")
	public ModelAndView addToCart(@PathVariable int id, Principal principal,@RequestParam int requestedQuantity,HttpSession session) {

		requestedQuantity=1;
		
		String userId = principal.getName();
		
		User u = userDao.getUserById(userId);
		
		Cart c = u.getCart();
		int cid = c.getCartId();
		
		System.out.println("hello");
		Product product = productDao.getProductById(id);
		System.out.println("hello");
		int p = product.getPrice();
		List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
		Iterator<CartItems> itr = list.iterator();

		while (itr.hasNext()) {
			CartItems citem = (CartItems) itr.next();
			System.out.println(citem.getProduct().getProductId() + citem.getCart().getCartId() + id);

			if (citem.getProduct().getProductId()==id ) {
				System.out.println("Match Found");
				
				citem.setQty(citem.getQty() + requestedQuantity);
				citem.setSubTotal(citem.getQty() * p);
				cartItemsDao.UpdateCartItems(citem);
				return new ModelAndView("redirect:/cart/viewCart");
			}
		} 

		CartItems ci = new CartItems();
		ci.setQty(requestedQuantity);
		ci.setStatus(true);
		ci.setProduct(product);
		ci.setSubTotal(requestedQuantity*p);
		ci.setCart(c);
		cartItemsDao.addCartItems(ci);
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		return new ModelAndView("redirect:/cart/viewCart");

	} // here we are adding the products to the cart with product price and quantity and viewing the products selected by the user 
	
	
	
	@RequestMapping(value = "/cart/deleteCart/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,HttpSession session,Principal userPrincipal) {
		System.out.println("delete is called");
		cartItemsDao.deleteCartItems(id);
		String userId = userPrincipal.getName();
		User u = userDao.getUserById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		return new ModelAndView("redirect:/cart/viewCart");
	} // here user are deleting their products once selected from cartitems ...
	
	@RequestMapping("/cart/checkout")
	public ModelAndView showShipmentform(Principal principal) {
		
		String userId = principal.getName();
		List<ShippingAddress> list=userDao.getShippingAddressByUserId(userId);
		if(list.isEmpty())
		{
			return new ModelAndView("shippingAddressForm", "command", new ShippingAddress());
		}
		else
		{
			
			return new ModelAndView("selectShippingAddressForm", "shipaddress", list);
		}
		
	} // here user is checking their selected products on cartitems.. and then they are proceeding for checkout by providing their delivery address ....
	
	@RequestMapping("/cart/getshippingform")
	public ModelAndView addNewShipAddress(Principal principal) {

		
		return new ModelAndView("shippingAddressForm", "command", new ShippingAddress());
	}
     /// here user is selecting their shipping address for delivery 
	
	
	@RequestMapping("/cart/createorder")
	public String saveOrder(  @ModelAttribute ShippingAddress shippingaddress, Model model, Principal principal,HttpSession session) {

		
		String userId = principal.getName();
		User u = userDao.getUserById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
	       List<CartItems> list1 = cartItemsDao.getCartItemsByCartId(cid);
	       Iterator itr = list1.iterator();
			while(itr.hasNext())
			{
				CartItems citem = (CartItems) itr.next();
				Product p=citem.getProduct();
				if((p.getQuantity()-citem.getQty())>=0)
				{
					System.out.println(p.getQuantity()-citem.getQty());
					p.setQuantity(p.getQuantity()-citem.getQty());
					productDao.updateProduct(p);
				}
				else
				{
					cartItemsDao.deleteCartItems(citem.getCartItemId());
					List<CartItems> ci = cartItemsDao.getCartItemsByCartId(cid);
					session.setAttribute("cartSize", ci.size());
					model.addAttribute("product",p);
					model.addAttribute("error","product out of stock");
					return "redirect:/cart/showCart";
				}
			} // here order is created based on the userid , product selected by the user  and selection of their delivery address
			
			List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
			Iterator itr1 = list.iterator();
			int grandTotal=0;
		       for(CartItems cartitem:list){//For each CartItem in list of cartitems
		    	   grandTotal=grandTotal+cartitem.getSubTotal();
		       }
		      
			shippingaddress.setUser(u);
			userDao.addShippingAddress(shippingaddress);   
		
		       
		      
		
		OrderDetails orderD=new OrderDetails();
		orderD.setOrderDetailsStatus("Ordered");
		orderD.setOrderDetails(new Date());
		orderD.setUser(u);
		orderD.setGrandTotal(grandTotal);
		orderDetailsDao.insertOrderDetails(orderD);
		
		

		while (itr1.hasNext()) {
			CartItems citem = (CartItems) itr1.next();
			if (citem.isStatus()) {
				System.out.println("Match Found");
				citem.setOrderDetails(orderD);
				cartItemsDao.UpdateCartItems(citem);
			}
			citem.setStatus(false);
			cartItemsDao.UpdateCartItems(citem);
		}
		
		System.out.println(orderD.getOrderDetailsId());
		List<CartItems> orderedList = cartItemsDao.getCartItemsByOrderId(orderD.getOrderDetailsId());
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		System.out.println(orderedList.isEmpty());
		model.addAttribute("order",orderD);
		model.addAttribute("shipping", shippingaddress);
		model.addAttribute("cartItems", orderedList);
		return "invoice";
	} // here final invoice is created based on user id , cart items and shipping adress  
	
	/*@RequestMapping("/invoice")
	public ModelAndView finalPage() {

		return new ModelAndView("invoice");
	}*/
	
	@RequestMapping(value = "/cart/generateinvoice/{id}", method = RequestMethod.GET)
	public String generateInvoice(@PathVariable int id,Model model,HttpSession session,Principal principal) {
		
	
		String userId = principal.getName();
		User u = userDao.getUserById(userId);
		Cart c = u.getCart();
		int cid = c.getCartId();
	       List<CartItems> list1 = cartItemsDao.getCartItemsByCartId(cid);
	       Iterator itr = list1.iterator();
			while(itr.hasNext())
			{
				CartItems citem = (CartItems) itr.next();
				Product p=citem.getProduct();
				if((p.getQuantity()-citem.getQty())>=0)
				{
					System.out.println(p.getQuantity()-citem.getQty());
					p.setQuantity(p.getQuantity()-citem.getQty());
					productDao.updateProduct(p);
				}
				else
				{
					cartItemsDao.deleteCartItems(citem.getCartItemId());
					List<CartItems> ci = cartItemsDao.getCartItemsByCartId(cid);
					session.setAttribute("cartSize", ci.size());
					model.addAttribute("product",p);
					model.addAttribute("error","product out of stock");
					return "redirect:/cart/showCart";
				}
			}
			
			List<CartItems> list = cartItemsDao.getCartItemsByCartId(cid);
			Iterator itr1 = list.iterator();
			int grandTotal=0;
		       for(CartItems cartitem:list){//For each CartItem in list of cartitems
		    	   grandTotal=grandTotal+cartitem.getSubTotal();
		       }
		       
		OrderDetails orderD=new OrderDetails();
		orderD.setOrderDetailsStatus("Ordered");
		orderD.setOrderDetails(new Date());
		orderD.setUser(u);
		orderD.setGrandTotal(grandTotal);
		orderDetailsDao.insertOrderDetails(orderD);
		
		

		while (itr1.hasNext()) {
			CartItems citem = (CartItems) itr1.next();
			if (citem.isStatus()) {
				System.out.println("Match Found");
				citem.setOrderDetails(orderD);
				cartItemsDao.UpdateCartItems(citem);
			}
			citem.setStatus(false);
			cartItemsDao.UpdateCartItems(citem);
		}
		
		System.out.println(orderD.getOrderDetailsId());
		List<CartItems> orderedList = cartItemsDao.getCartItemsByOrderId(orderD.getOrderDetailsId());
		
		List<CartItems> citem = cartItemsDao.getCartItemsByCartId(cid);
		session.setAttribute("cartSize", citem.size());
		
		System.out.println(orderedList.isEmpty());
		model.addAttribute("order",orderD);
		model.addAttribute("shipping", userDao.getShippingAddressById(id));
		model.addAttribute("cartItem", orderedList);
		return "invoice";
		
	} // here final checkout is done by creating invoice of the products selected by the users along with total price ..
	

}
