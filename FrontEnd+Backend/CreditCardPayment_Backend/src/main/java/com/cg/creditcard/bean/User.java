package com.cg.creditcard.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.creditcard.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "userTable")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "userId")
	private long userId;
	
	
	@Column(name = "password")
	@NotEmpty(message = "Password should contain min of 3 chars and max of 10 chars")
//	@Size(min=3, max=10)
	private String password;
	
	@Column(name = "role")
//	@NotNull(message="Role can be either ADMIN or CUSTOMER")
	private UserRole role;
	
	@Column(name = "name")
	@NotEmpty
//	@Size(min = 6, message = "Name should have atleast 6 characters")
	private String name;
	
	@Column(name = "email")
	@NotEmpty(message = "Email cannot be blank")
	@Email
	private String email;
	
	@Column(name = "contactNo")
	@Min(value = 1000000000l,message = "Phone number should be 10 characters long")
	@Max(value = 9999999999l,message = "Phone number should be 10 characters long")
	private Long contactNo;
	
	@Column(name = "dob")
//	@DateTimeFormat(pattern="yyyy/MM/dd")
	private  LocalDate dob;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<CreditCard> creditcards=new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Account> accounts=new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Statement> statements=new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Transaction> transactions=new ArrayList<>();
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@JsonManagedReference
	public List<CreditCard> getCreditcards() {
		return creditcards;
	}

	public void setCreditcards(List<CreditCard> creditcards) {
		this.creditcards = creditcards;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	@JsonManagedReference
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	@JsonManagedReference
	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	@JsonManagedReference
	public List<Transaction> getUserTransaction() {
		return transactions;
	}

	public void setUserTransaction(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
