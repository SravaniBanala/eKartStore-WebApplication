package edu.northeastern.csye6220.eKartFinalProject.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cart_table")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cart_ID", unique=true, nullable = false)
	private int cartID;
	
	@Column(name="user_ID")
	private int userID;
	
	@OneToOne
	@JoinColumn(name="Product_ID")
	Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Transient
	private int totalPrice;

	public Cart(){}
	
	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int gettotalPrice() {
		return cartID;
	}

	public void settotalPrice(int cartID) {
		this.cartID = cartID;
	}
	
	

		
}
