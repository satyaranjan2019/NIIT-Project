package com.niit.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.DAO.CategoryDao;
import com.niit.DAO.ProductDao;
import com.niit.model.Category;
import com.niit.model.Product;

@Controller
@SuppressWarnings("unused")

public class ProductController {
	
	 
	  
		private static final ProductDao productdao = null;
		@Autowired 
		  CategoryDao categoryDao;
		@Autowired 
		  ProductDao productDao;
		  
		@RequestMapping("/AddProduct")
		public String showProductform(Model model) 
		{
			List<Category> categories=categoryDao.getAllCategory();
			model.addAttribute("product",new Product());
			model.addAttribute("categories",categories);
			System.out.println("Size of category list " + categories.size());
			
			return "AddProduct";
			//return new ModelAndView("AddProduct", "command", new Product());
		}
		@RequestMapping(value="/saveProduct",method = RequestMethod.POST)  
	    public String saveProduct(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model,HttpServletRequest request,@RequestParam("file") MultipartFile file , HttpSession session)
	    {
	 	System.out.println("aaaaa "+ product.getProductName());
		 
		 if(result.hasErrors()){//hasErrors return true if product details in not valid
			 System.out.println(result.hasErrors());
				model.addAttribute("categories",categoryDao.getAllCategory());
				return "AddProduct";
			}
		 byte fileBytes[];
			FileOutputStream fos = null;

			String fileName = "";
			String productImage = "";
			ServletContext context = request.getServletContext();
			String realContextPath = context.getRealPath("/");
			String pn = product.getProductName();
			if (file != null) {
				fileName = realContextPath + "/resources/image/" + pn + ".jpg";
				
				File fileobj = new File(fileName);
				try {
					fos = new FileOutputStream(fileobj);
					fileBytes = file.getBytes();
					fos.write(fileBytes);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}//return to ModelAndView("productForm")page here we are updating a product by adding its details along with images which are stored in resource folder * sub folder in webapp folder... 

			product.setProductImage(productImage);
			product.setProductStatus(true);	
			
	                productDao.addProduct(product);
	                productImage = "resources/image/" + product.getProductName() + ".jpg";
	                product.setProductImage(productImage);
	                productDao.updateProduct(product);
	                return "redirect:/DisplayProduct";
		 	
	    }
 
 @RequestMapping(value="/EditProduct/{id}")
	public String getUpdateProductForm(@PathVariable int id,Model model){
		Product product=productDao.getProductById(id);
		List<Category> categories=categoryDao.getAllCategory();
		model.addAttribute("product",product);
		model.addAttribute("categories",categories);
		return "EditProduct";
	}
 
 @RequestMapping("/DisplayProduct")
	public ModelAndView productlist() 
 {
		List<Product> listProd = productDao.getAllProducts();
		return new ModelAndView("DisplayProduct","productlist",listProd);
} //return new ModelAndView("DisplayProduct") here we are viewing the products added by admin ....
 
 @RequestMapping(value="/saveOrUpdateProduct",method = RequestMethod.POST)  
 public String saveOrUpdateProduct(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model,HttpServletRequest request)
 {
	 
	 if(result.hasErrors()){//hasErrors return true if product details in not valid
		 System.out.println(result.hasErrors());
			model.addAttribute("categories",categoryDao.getAllCategory());
			return "AddProduct";
		} 
	 	productDao.saveOrUpdateProduct(product);
	 	MultipartFile prodImage=product.getImage();//image uploaded in the productform.jsp
	 	System.out.println("ZZZZZZZZZ"+prodImage.toString());
	 	String productImage = "";
	 	if(prodImage!=null && !prodImage.isEmpty()){
			//how to get rootdirectory
			String rootdirectory=request.getServletContext().getRealPath("/");
			System.out.println("Root Directory " + rootdirectory);
			//create a path
			Path paths=Paths.get(rootdirectory+"/resources/image/"+product.getProductName()+".jpg");
			System.out.println("path "+paths.toString());
				//it throws checked exception
				try {
					prodImage.transferTo(new File(paths.toString()));
					System.out.println("images added");
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	 	productImage = "resources/image/" + product.getProductName() + ".jpg";
		product.setProductImage(productImage);
		product.setProductStatus(true);	
		  productDao.saveOrUpdateProduct(product);
          return "redirect:/DisplayProduct";
		
		//return new ModelAndView("DisplayProduct") here admin is viewing the products and if necesary admin is editing the product details and saving it and again viewing the updated product list ..
 }
 @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		System.out.println("information deleted ");
		productDao.deleteProduct(id);
		return new ModelAndView("redirect:/DisplayProduct");
	} //return new ModelAndView("DisplayProduct") here admin is deleting a product from databse and again calling up the updated product list after deleting a particular product along with details ... 

 @RequestMapping(value="/viewCategoryProduct/{id}")
	public ModelAndView viewProductByCategoryID(@PathVariable int id,HttpSession session) {
		List<Product> listProdbyCid = productDao.listByCategoryId(id);
		session.setAttribute("productList", listProdbyCid);
		return new ModelAndView("viewCategoryProduct","productList",listProdbyCid);
 }


	@RequestMapping(value="/ProductDetails/{id}")  
 public ModelAndView edit(@PathVariable int id,HttpSession session){  
      Product product=productDao.getProductById(id);
      session.setAttribute("productDescription", product);
     return new ModelAndView("ProductDetails","productDescription",product);  
 } //return new ModelAndView("viewCategoryProduct") here we are retrieving the product details on the basis of Id...

	
	
	
}
