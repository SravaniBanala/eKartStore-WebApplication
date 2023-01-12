package edu.northeastern.csye6220.eKartFinalProject.pojo;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import edu.northeastern.csye6220.eKartFinalProject.pojo.Cart;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
    private int userId;
	
	@Column(nullable = false, length = 64)
	private String firstName;
	
	@Column(nullable = false, length = 64)
	private String lastName;
     
    @Column(nullable = false, unique = true, length = 64)
    private String email;
    
    @Column(nullable = false, length = 45)
	private String role;
     
    @Column(nullable = false, length = 64)
    private String password;
    	
    
	public User(String firstName, String lastName, String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role=role;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
	
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
