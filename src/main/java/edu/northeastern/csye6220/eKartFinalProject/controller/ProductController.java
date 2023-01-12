package edu.northeastern.csye6220.eKartFinalProject.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.northeastern.csye6220.eKartFinalProject.dao.CategoryDAO;
import edu.northeastern.csye6220.eKartFinalProject.dao.ProductDAO;
import edu.northeastern.csye6220.eKartFinalProject.exception.ProductException;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Product;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Category;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	CategoryDAO categoryDao;
	
	@Autowired
	ServletContext servletContext;
	
	@PostMapping(value = "/product/add")
	protected ModelAndView registerNewProduct(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		System.out.println("Product file name is "+request.getParameter("filename"));
		product.setFileName(request.getParameter("filename"));
		System.out.println("The category name sent is "+request.getParameter("category"));
		System.out.println("Register a New Product Under Category ");
		
    	try {
                   boolean isFileuploaded = uploadFile(product);
                   
                   if(isFileuploaded) {
                    
                    System.out.print("Saving New Product under Category");  

                    Category category=categoryDao.get(request.getParameter("category"));
                    product.setCategory(category);
                    
                    Product prd = productDao.createProduct(product);
            
                    return new ModelAndView("product-add-success", "product", prd);
                   }
                
               else {
                    System.out.println("Failed to create directory!");
                    return new ModelAndView("error", "errorMessage", "error while creating directory");
                }

            
            }
    	catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating directory ");
		}
	}	
	
  		
	
	
	@GetMapping(value = "/product/add")
	public ModelAndView getProductForm(HttpServletRequest request) throws Exception  
	{				
		ModelAndView mv=new ModelAndView("product-form");
		List<Category> categoryList;
		System.out.println("Inside the product controller for getting product form ");
		categoryList=  categoryDao.getCategoryList();
		mv.addObject("categoryList", categoryList);
		mv.addObject("product", new Product());
		return mv;
	}
	
	@GetMapping(value = "/list")
	public ModelAndView viewProduct(HttpServletRequest request) throws Exception {		
		ModelAndView mv=new ModelAndView();
		
		int pageSize = 1;
		int  pageNumber;
		String page = request.getParameter("pagenum");
		if(page == null) {
			System.out.println("pagenumber"+page);
			pageNumber = 1;
		}
		else
		{
			System.out.println("pagenumber"+page);
			pageNumber = Integer.parseInt(page);
		}
		System.out.println("pagenumber final "+pageNumber);
		List<Product> productList;
		System.out.println("Inside the product controller");
		productList=productDao.productList(pageNumber,pageSize);
		mv.addObject("productList", productList);
		mv.setViewName("product-view");
		return mv;
	}
	
	
	@GetMapping(value = "/product/update")
	public ModelAndView updateProduct(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView("product-update");
		List<Category> categoryList;
		Product p= (Product)(session.getAttribute("productSelected"));
		categoryList= categoryDao.getCategoryList();
		mv.addObject("categoryList", categoryList);
		
		mv.addObject("product", p);
		
		return mv;

	}
	
	
	@PostMapping(value = "/product/update")
	protected ModelAndView updateProduct(HttpServletRequest request, HttpSession session,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {

		System.out.println("Inside the product controller");
	

		try 
		{

			System.out.println("update Added NewProduct");
			String action;
			action=request.getParameter("action");
			
			System.out.println(request.getParameter("productSelected"));
			
			if(action.equals("Update Product"))
			{			
			ModelAndView modelView=new ModelAndView("product-update");
			List<Category> categoryList;
			Product p= productDao.getProductById(Integer.parseInt(request.getParameter("productSelected")));
			categoryList= categoryDao.getCategoryList();
			modelView.addObject("categoryList", categoryList);
			modelView.addObject("product", p);
			
			return modelView;
			}
			
			if(action.equals("Delete"))
			{			
			ModelAndView modelView=new ModelAndView("product-delete-success");
			
			Product p= productDao.getProductById(Integer.parseInt(request.getParameter("productSelected")));
			System.out.println("Product received is "+p.getProductName());
			productDao.deleteProduct(p);    
			
			return modelView;
			}

			
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "Cannot Delete This Item. It is associated to a Cart or an Order!");
		}
	
		return null;
	}	
	
	@PostMapping(value = "/product/updatesuccess")
	protected ModelAndView updateSuccessCategory(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
			int productID=Integer.parseInt(request.getParameter("productID"));
			System.out.println("Product ID selected is "+productID);
			System.out.println("Inside the product Update Completion controller");
			System.out.println("Product file name is "+request.getParameter("filename"));
			product.setFileName(request.getParameter("filename"));

			try {
				 boolean isFileuploaded = uploadFile(product);
                 
                 if(isFileuploaded) {
                  
                  System.out.print("Saving New Product under Category");  
                  
                  Category category= categoryDao.get(request.getParameter("category"));
                  product.setCategory(category);

  				  productDao.updateProduct(product);
  				
  				return new ModelAndView("product-update-success");
                 }
              
             else {
                  System.out.println("Failed to create directory!");
                  return new ModelAndView("error", "errorMessage", "error while creating directory");
              }


	        } catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while creating directory ");
			}
		}	

	
	protected boolean uploadFile(Product product){
		try {
            if (product.getFileName().trim() != "" || product.getFileName() != null) {
                File directory;
                String check = File.separator; // Checking if system is linux
                                                // based or windows based by
                                                // checking seprator used.
                String path = null;
                if (check.equalsIgnoreCase("\\")) {
                    path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as DemoApp/build/web/
                                                                                  // so we need to replace build in the path
                                                                                        }

                if (check.equalsIgnoreCase("/")) {
                    path = servletContext.getRealPath("").replace("build/", "");
                    path += "/"; // Adding trailing slash for Mac systems.
                }
                
                System.out.println("File patht" + path);
				directory = new File(path + "/images");    // Creating Directory images under which images would be stored
 
                //System.out.println("Directory Path " + directory);
                boolean temp = directory.exists();
                if (!temp) {
                    temp = directory.mkdir();
                }
                if (temp) {
					
                    // We need to transfer to a file
                	MultipartFile photoInMemory = product.getPhoto();

                    String fileName = photoInMemory.getOriginalFilename();
                    // could generate file names as well

                    File localFile = new File(directory.getPath(), fileName);

                    // move the file from memory to the file

                    photoInMemory.transferTo(localFile);
                    
                    System.out.println("File is stored at" + localFile.getPath());
                    
                    System.out.print("Saving New Product under Category");  
                    
                    System.out.print("File name of uploaded Image "+ product.getFileName());  
                    
                    product.setFileName(fileName);
                    
                    System.out.print("File name of uploaded Image "+ product.getFileName()); 
                     return true;
                
                } else {
                    System.out.println("Failed to create directory!");
                    return false;
                }
                           
            }
            return false;

        } catch (IllegalStateException e) {
            System.out.println("*** IllegalStateException: " + e.getMessage());
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("*** IOException: " + e.getMessage());
            return false;
        }
		
		
	}
		
	@PostMapping(value = "/product/productselect")
	public ModelAndView selectProductForm(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView modelView=new ModelAndView("customer-select-product");
		List<Category> categoryList;
		System.out.println("Category selected is="+request.getParameter("categorySelected"));
		Category category= categoryDao.getCategory(Integer.parseInt(request.getParameter("categorySelected")));
		session.setAttribute("category", category);
		List<Product> productList;
		System.out.println("Inside the customer product controller");
		productList=productDao.productListByCategory(Integer.parseInt(request.getParameter("categorySelected")));
		System.out.println("Size of the product list is "+productList.size());
		modelView.addObject("productList", productList);
		
		return modelView;
	}
	
}
