package edu.northeastern.csye6220.eKartFinalProject.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Product;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Cart;
import edu.northeastern.csye6220.eKartFinalProject.exception.CartException;

@Component
public class CartDAO extends DAO {

	public List<Cart> cartListByUser(int userID) throws CartException {
        try {
            begin();
            System.out.println("User ID in the DAO is "+userID);
            Query q = getSession().createQuery("from Cart c where c.userID= :userID");
			q.setInteger("userID", userID);
            List<Cart> list = q.list();
            commit();
            return list;
        } 
        
        catch (HibernateException e) {
            rollback();
            throw new CartException("Could not list the product", e);
        }
    }
    
	
	public void addToCart(Cart cart) throws CartException {
		try {
			begin();
				
			getSession().save(cart);
				
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not get cartItems ", e);
		}
	}
	
	
	public void updateCart(int quantity, int userID, Product product) throws CartException {
		try {
			begin();
			Query query = getSession().createQuery("update Cart set quantity = :quantity where userID= :userID and product= :product");
			query.setInteger("quantity",quantity );
			query.setInteger("userID", userID);
			query.setEntity("product", product);
			query.executeUpdate();

			System.out.println("In the update cart DAO");
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not get cartItems ", e);
		}
	}
	
	
	public void deleteItem(Cart cart) throws CartException {
		try {
			begin();
			getSession().delete(cart);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not get cartItems ", e);
		}
	}
   
}