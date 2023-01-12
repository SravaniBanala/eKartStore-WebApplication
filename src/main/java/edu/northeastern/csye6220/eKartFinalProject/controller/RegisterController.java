package edu.northeastern.csye6220.eKartFinalProject.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class RegisterController {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	UserValidator userValidator;
	
	@GetMapping(value = "user/register.htm")
	public ModelAndView register() {			
		return new ModelAndView("registerUser", "user", new User());
	}
	
	@PostMapping(value = "user/register")
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		
		HttpSession session = (HttpSession) request.getSession();

			userValidator.validate(user, result);

			if (result.hasErrors()) {
				return new ModelAndView("registerUser", "user", user);
			}

			try 
			{

				System.out.print("registerNewUser");

				User u = userDao.register(user);
				System.out.println("User role is "+user.getRole());
				
				if(u == null){
					System.out.println("email/Password does not exist");
					session.setAttribute("errorMessage", "email/Password does not exist");
					return new ModelAndView("error");
				}
				
				
				return new ModelAndView("loginUser", "successMsg", "successfully Registered");

			} 
			catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
		}
}
