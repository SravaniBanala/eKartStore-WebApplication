package edu.northeastern.csye6220.eKartFinalProject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false,unique=true)
	private int categoryId;

	@Column(nullable = false)
	private String categoryName;

	public Category(String categoryName)
	{
		this.categoryName=categoryName;
	}
	
	public Category()
	{
		
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
}
