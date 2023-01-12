package edu.northeastern.csye6220.eKartFinalProject.controller;

import java.util.List;
import java.util.Locale;


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

import edu.northeastern.csye6220.eKartFinalProject.exception.CategoryException;
import edu.northeastern.csye6220.eKartFinalProject.validator.CategoryValidator;
import edu.northeastern.csye6220.eKartFinalProject.dao.CategoryDAO;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Category;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/category/*")
public class CategoryController {
	@Autowired
	CategoryValidator categoryValidator;
	
	@Autowired
	CategoryDAO categoryDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(categoryValidator);
	}

	@PostMapping(value = "/category/add")
	public ModelAndView addNewCategory(@ModelAttribute("category") Category category, BindingResult result) throws Exception {
		
		categoryValidator.validate(category, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("addcategory-form", "category", category);
		}

		try {				
			category = categoryDAO.create(category.getCategoryName());
		} catch (CategoryException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		return new ModelAndView("addcategory-success", "category", category);
		
	}

	@GetMapping(value = "/category/add")
	public ModelAndView getCategoryForm() throws Exception {			
		return new ModelAndView("addcategory-form", "category", new Category());
	}
	
	
	@GetMapping(value = "/category/select")
	public ModelAndView selectCategoryForm(HttpServletRequest request) throws Exception {	
		ModelAndView modelView = new ModelAndView("searchcategory-form");
		List<Category> categoryList;
		System.out.println("Inside the customer category controller");
		categoryList=categoryDAO.getCategoryList();
		modelView.addObject("categoryList", categoryList);
		
		return modelView;
	}	
	
}
