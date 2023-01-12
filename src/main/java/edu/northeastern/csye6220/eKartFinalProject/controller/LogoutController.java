package edu.northeastern.csye6220.eKartFinalProject.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/logout*")
public class LogoutController {

	
	@Autowired
	ServletContext servletContext;
	
	@PostMapping
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		 // Get the current session
	    HttpSession session = request.getSession();

	    // Check if the session is valid
	    if (session != null && session.getAttribute("logginuser") != null) {
	        // Invalidate the session
	        session.invalidate();

	        // Redirect the user to the login page
	        return new ModelAndView("loginUser");
	    } else {
	        // The session is not valid or the user is not logged in
	        // Redirect the user to the login page
	    	return new ModelAndView("loginUser");
	    }
    }
}
