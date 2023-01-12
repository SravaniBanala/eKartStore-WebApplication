package edu.northeastern.csye6220.eKartFinalProject.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Product;

import edu.northeastern.csye6220.eKartFinalProject.pojo.User;

import edu.northeastern.csye6220.eKartFinalProject.validator.CategoryValidator;
import edu.northeastern.csye6220.eKartFinalProject.dao.CartDAO;
import edu.northeastern.csye6220.eKartFinalProject.dao.UserDAO;
import edu.northeastern.csye6220.eKartFinalProject.dao.ProductDAO;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Cart;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	
	@Autowired
	CartDAO cartDao;

	@Autowired
	UserDAO  userDao;
	
	@Autowired
	ProductDAO productDao;
	

	@PostMapping(value = "/cart/insert")
	public ModelAndView addCart(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {		
		HttpSession session = (HttpSession) request.getSession();
		
		ModelAndView modelView = new ModelAndView("Viewcart-page");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		int productId= Integer.parseInt(request.getParameter("product"));
		
		Product selectedProduct=productDao.getProductById(productId);
		
		System.out.println("Product Name selected is "+productId);
		
		User u=(User) session.getAttribute("logginuser");
		int userID=u.getUserId();
		
		System.out.println("Product Name selected is "+userID);
		String action=request.getParameter("selection");
		
		if(action.equals("Add to Cart"))
			
		{
		List<Cart> previousItems=cartDao.cartListByUser(userID);
		
		System.out.println("Previous Items Size "+previousItems.size());
		
		//add new cart items			
		
		cart.setProduct(selectedProduct);
		cart.setQuantity(quantity);
		cart.setUserID(userID);
	    List<Cart> cartItems=new ArrayList<Cart>();
        List<Cart> addedCart=new ArrayList<Cart>();
		
		boolean issimilarItem=false;
		boolean isexists=false;
		
		for(Cart c: previousItems)
		{
			if(c.getProduct().getProductID()==cart.getProduct().getProductID())
			{
				issimilarItem=true;
				int itemCount = c.getQuantity()+cart.getQuantity();
				int userId=cart.getUserID();
				Product product=cart.getProduct();
				cartDao.updateCart(itemCount, userId, product);
				c.setQuantity(c.getQuantity()+cart.getQuantity());
				break;
			}
		}
		
		if(issimilarItem==false)
		{
					for(Cart c: cartItems)
			{
				if(c.getProduct().getProductID()==cart.getProduct().getProductID())
				{
					isexists=true;
					break;
				}
			}
			if(isexists==false)
			{
				cartItems.add(cart);
				cartDao.addToCart(cart);
			}
		
		}
	
		addedCart=cartDao.cartListByUser(userID);
		
		double itemsTotalAmount= 0;
		double shipping = 8.5;
		double taxRate = 7;
		
		for(Cart c: addedCart)
		{
			itemsTotalAmount+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
     	double orderTotal=(1+(taxRate/100))*itemsTotalAmount+shipping;
		double taxes=itemsTotalAmount*(taxRate/100);
        session.setAttribute("itemTotal", itemsTotalAmount);
		
		
		
        modelView.addObject("size", cartSize);
        modelView.addObject("addedCart", addedCart);
        modelView.addObject("itemTotal", itemsTotalAmount);
        modelView.addObject("shipping", shipping);
        modelView.addObject("taxes",taxes);
        modelView.addObject("orderTotal",orderTotal);
		
		
		return modelView;
		}
	
		return modelView;	
				
	}
	
	
	@GetMapping(value = "/cart/viewCart")
	public ModelAndView addToCartGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView modelView=new ModelAndView("Viewcart-page");
		
		User u=(User) session.getAttribute("logginuser");
		int userID=u.getUserId();
		
		System.out.println("Product Name selected is "+userID);
		List<Cart> addedCart=cartDao.cartListByUser(userID);
		
		
		double itemsTotalAmount= 0;
		double shipping = 8.5;
		double taxRate = 7;
		
		for(Cart c: addedCart)
		{
			itemsTotalAmount+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
     	double orderTotal=(1+(taxRate/100))*itemsTotalAmount+shipping;
		double taxes=itemsTotalAmount*(taxRate/100);
        session.setAttribute("itemTotal", itemsTotalAmount);
		
		
		
        modelView.addObject("size", cartSize);
        modelView.addObject("addedCart", addedCart);
        modelView.addObject("itemTotal", itemsTotalAmount);
        modelView.addObject("shipping", shipping);
        modelView.addObject("taxes",taxes);
        modelView.addObject("orderTotal",orderTotal);
		
		
		return modelView;
	
	}
	
	@PostMapping(value = "/cart/update*")
	public ModelAndView updateCart(HttpServletRequest request,HttpSession session, @ModelAttribute("cart") Cart cart) throws Exception {	
		ModelAndView modelView = new ModelAndView("Viewcart-page");
		
		String action= request.getParameter("action");
		int productID=Integer.parseInt(request.getParameter("productID"));
		
		if(action.equals("Delete"))
		{
			cartDao.deleteItem(cart);
			
		}
	
		
		List<Cart> addedCart=cartDao.cartListByUser(cart.getUserID());
		double itemsTotalAmount= 0;
		double shipping = 8.5;
		double taxRate = 7;
		
		for(Cart c: addedCart)
		{
			itemsTotalAmount+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
		
		double orderTotal=(1+(taxRate/100))*itemsTotalAmount+shipping;
		double taxes=itemsTotalAmount*(taxRate/100);
				
		modelView.addObject("size", cartSize);
		modelView.addObject("addedCart", addedCart);
		modelView.addObject("itemTotal", itemsTotalAmount);
		modelView.addObject("shipping", shipping);
		modelView.addObject("taxes",taxes);
		modelView.addObject("orderTotal",orderTotal);
	
		return modelView;
	}
	
	
	
	
}
