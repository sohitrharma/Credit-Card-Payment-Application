package com.cg.creditcard.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;



/*
 * 
 * @author Anisa
 * 
 * */




//This annotation indicates this class is a entity class
@Entity 

//This annotation is used to create and declare the table name
@Table(name="Payment")
public class Payment 
{
	
	// @Id is use to declare primary key in the table 
	// @GeneratedValue will give auto generated value 
	// @SequenceGenerator with generated value will increase the size of next value by 1
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ")
    @SequenceGenerator(sequenceName = "payment_seq", allocationSize = 1, name = "PAYMENT_SEQ")
    @Column(name="payment_Id")
    private long paymentId;
    
    //This annotation is used to create and declare the column name
    
    @Column(name="Status")
    private String status="Completed"; 
    @NotNull
//    @Positive(message = "Payment amount cannot be negative .Please enter valid amount")
    @Column(name="Payment_Amount")
    double paymentAmount;
    
    @NotNull(message="Please enter the  transaction date in the format(dd/MM/yyyy).")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent
    @Column(name="payment_date")
    private LocalDate date = LocalDate.now();
	
	
	@ManyToOne
	@JoinColumn(name="cardId",nullable=true)
//	@JsonIgnore
	private CreditCard creditcard;
	
//	@OneToOne
//	private Transaction transaction;
	
	

	//Getters and setters
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@JsonBackReference
	public CreditCard getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(long paymentId, String status, @NotNull double paymentAmount,
			@NotNull(message = "Please enter the  transaction date in the format(dd/MM/yyyy).") @PastOrPresent LocalDate date,
			CreditCard creditcard) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.paymentAmount = paymentAmount;
		this.date = date;
		this.creditcard = creditcard;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditcard == null) ? 0 : creditcard.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paymentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (paymentId ^ (paymentId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (creditcard == null) {
			if (other.creditcard != null)
				return false;
		} else if (!creditcard.equals(other.creditcard))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(paymentAmount) != Double.doubleToLongBits(other.paymentAmount))
			return false;
		if (paymentId != other.paymentId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", status=" + status + ", paymentAmount=" + paymentAmount + ", date="
				+ date + ", creditcard=" + creditcard + "]";
	}
	
	
	
	
	
}