package edu.northeastern.csye6220.eKartFinalProject.controller;

import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import edu.northeastern.csye6220.eKartFinalProject.dao.UserDAO;
import edu.northeastern.csye6220.eKartFinalProject.exception.UserException;
import edu.northeastern.csye6220.eKartFinalProject.validator.UserValidator;
import edu.northeastern.csye6220.eKartFinalProject.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
public class LoginController {
	@Autowired
	UserDAO userDao;
	
	@Autowired
	UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping(value = "/")
	public ModelAndView home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return new ModelAndView("loginUser");
	}
	
	@PostMapping(value = "user/login")
	public ModelAndView loginUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
	
		System.out.println("Inside the user controller");
		try {
				System.out.print("Inside the Login Controller loginUser");
				
				User u = userDao.getLoggedInUser(request.getParameter("email"), request.getParameter("password"));
				
				if(u == null){
					System.out.println("Email/Password does not exist");
					return new ModelAndView("error", "errorMessage", "email/Password is incorrect or does not exist");
				}	
				
						
				else if(u.getRole().equals("employer"))
				{			
				session.setAttribute("logginuser", u);
				
				return new ModelAndView("employer-home", "user", u);
				}

				else if(u.getRole().equals("customer"))
				{
					session.setAttribute("logginuser", u);
					return new ModelAndView("customer-home", "user", u);
							
				}
				
				System.out.println("logged in user is " + u);
				System.out.println("Logged in User FirstName"+u.getFirstName());
				
			}
			catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login" + e);
			}

			return null;
		}
	
	// user home page from session
	@GetMapping(value = "user/login")
	public ModelAndView loggedinUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		
		System.out.println("Inside the user controller");
		
		try {
				System.out.print("Retrievigng User LoginUser From Session");
				User u = (User) session.getAttribute("logginuser");
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				return new ModelAndView("error", "errorMessage", "error while retrieving session");
			}
			

			else if(u.getRole().equals("employer"))
			{			
			session.setAttribute("employer", u);
			
			return new ModelAndView("employer-home", "user", u);
			}

			else if(u.getRole().equals("customer"))
			{
				session.setAttribute("customer", u);
				return new ModelAndView("customer-home", "user", u);
						
			}
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login" + e);
		}

		return null;
	}
}
