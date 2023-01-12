package edu.northeastern.csye6220.eKartFinalProject.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import edu.northeastern.csye6220.eKartFinalProject.dao.CartDAO;
import edu.northeastern.csye6220.eKartFinalProject.pojo.PDFView;
import edu.northeastern.csye6220.eKartFinalProject.pojo.User;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/viewpdf/*")
public class CheckOutController extends PDFView{

	
	@Autowired
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	@PostMapping(value = "/viewpdf/checkout")
	public ModelAndView showPdfReport(@ModelAttribute("cart") Cart cart,
									  ModelMap model,
			                          BindingResult result, 
			                          HttpServletRequest request) throws Exception
	{
		HttpSession session = (HttpSession) request.getSession();
		
		User u=(User) session.getAttribute("logginuser");
		int userID=u.getUserId();
		List<Cart> viewCart=cartDao.cartListByUser(userID);
		for(Cart c: viewCart)
		{
		
		cartDao.deleteItem(c);
		
		}
		
		model.addAttribute("cartitems", viewCart);
		View v = new PDFView();
		return new ModelAndView(v);
	}
	
}
