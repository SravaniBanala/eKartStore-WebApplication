package edu.northeastern.csye6220.eKartFinalProject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import edu.northeastern.csye6220.eKartFinalProject.pojo.Cart;


@Entity
@Table(name="products")
public class Product {
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable = false)
	private int productID;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private double productPrice;

	@Transient
	private MultipartFile photo;          //for DataBinder to bind <input type="file".../>
										  //will not be mapped for Hibernate as we store the file in the FileSystem
										  //file will be placed into this field by DataBinder
										  //file is in the memory. needs to be transferred to the FileSystem using java.io.file
	@Column
	private String fileName;   
	
	@ManyToOne
    @JoinColumn (name = "categoryId")
	private Category category;
	
	
	public Product(String productName, double productPrice) {
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public Product() {
	
	}	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}	

	public int getProductID() {
		return productID;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}	

}
