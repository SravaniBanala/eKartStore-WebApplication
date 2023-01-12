package edu.northeastern.csye6220.eKartFinalProject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.northeastern.csye6220.eKartFinalProject.dao.CategoryDAO;
import edu.northeastern.csye6220.eKartFinalProject.exception.CategoryException;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Category;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	//@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	public boolean supports(Class aClass) {
		return Category.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Category newCategory = (Category) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "error.invalid.category", "Category Required");
		
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
        
	
		try {
			Category c = categoryDAO.get(newCategory.getCategoryName());
			if(c !=null)
				errors.rejectValue("categoryName", "error.invalid.category", "Category already Exists");
			
		} catch (CategoryException e) {
			System.err.println("Exception in Category Validator");
		}
		
		
		
	
	}
}
