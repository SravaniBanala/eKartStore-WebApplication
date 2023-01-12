package edu.northeastern.csye6220.eKartFinalProject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import edu.northeastern.csye6220.eKartFinalProject.pojo.Category;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Product;
import edu.northeastern.csye6220.eKartFinalProject.exception.ProductException;

@Component
public class ProductDAO extends DAO {

	 public Product createProduct(Product product) throws ProductException {
	        try {
	            begin();            
	            getSession().save(product);     
	            commit();
	            return product;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create advert", e);
	            throw new ProductException("Exception while creating advert: " + e.getMessage());
	        }
	    }

	  
	 public void updateProduct(Product product) throws ProductException {
	        try {
	            begin();
	            getSession().update(product);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProductException("Could not save the product", e);
	        }
	    }

	 
	 
	    public void deleteProduct(Product product) throws ProductException {
	        try {
	            begin();
	            getSession().delete(product);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProductException("Could not delete the product", e);
	        }
	    }
	    
	    public Product getProductById(int productID) throws ProductException {
			try {
				begin();
				Query q = getSession().createQuery("from Product where productID= :productID");
				q.setInteger("productID", productID);
				
				Product product= (Product) q.uniqueResult();
				//System.out.println("Category received is "+category.getCategoryName());
				commit();
				return product;
				
			} catch (HibernateException e) {
				rollback();
				throw new ProductException("Could not get Product " + productID, e);
			}
		}
	    
	    public List<Product> productList(int pageNumber, int pageSize) throws ProductException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Product");
	            q.setFirstResult((pageNumber - 1) * pageSize);
	            q.setMaxResults(pageSize);
	            List<Product> list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProductException("Could not list the product", e);
	        }
	    }
	    
	    
	    public List<Product> productListByCategory(int categoryId) throws ProductException {
	        try {
	            begin();
	            //long categoryID = category.getCategoryId();
	            Query q = getSession().createQuery("from Product p where p.category.categoryId = :categoryID");
				//q.setInteger("categoryID", categoryID);
				q.setParameter("categoryID", categoryId);
	            List<Product> list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProductException("Could not list the product", e);
	        }
	    }
}