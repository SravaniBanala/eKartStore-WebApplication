package edu.northeastern.csye6220.eKartFinalProject.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import edu.northeastern.csye6220.eKartFinalProject.exception.CategoryException;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Category;

@Component
public class CategoryDAO extends DAO {

    public Category create(String categoryName) throws CategoryException {
        try {
            begin();
            System.out.println("inside Category DAO");
            Category category = new Category(categoryName);
            getSession().save(category);
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
    }
    
    public Category get(String categoryName) throws CategoryException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where categoryName= :categoryName");
            q.setString("categoryName",categoryName);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " + categoryName + " " + e.getMessage());
        }
    }
    
    public Category getCategory(int categoryId) throws CategoryException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where categoryId= :categoryId");
            q.setInteger("categoryId",categoryId);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " + categoryId + " " + e.getMessage());
        }
    }

    public List<Category> getCategoryList() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }

    public void update(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws CategoryException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not delete the category", e);
        }
    }
    
   
}