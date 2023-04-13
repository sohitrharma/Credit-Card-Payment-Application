package com.cg.creditcard.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
@Table(name = "Transaction")
public class Transaction {

	// @Id is use to declare primary key in the table
	// @GeneratedValue will give auto generated value
	// @SequenceGenerator with generated value will increase the size of next value
	// by 1
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAN_SEQ")
    @Column(name="transaction_Id")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "TRAN_SEQ")
    private long tranId;
   
   
    //This annotation is used to create and declare the column name
    @Column(name="status")
    private String status ="Completed";
   
    @NotNull(message="Please enter the  transaction date in the format(dd/MM/yyyy).")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent
    @Column(name="transaction_date")
    private LocalDate date=LocalDate.now();
   
   
    @NotNull(message="Please enter the  transaction time in the format(HH:mm:ss).")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(name="transaction_time")
    private LocalTime time=LocalTime.now();
   
    @Size(min=16,max=16,message = "Please enter valid card number")
    @NotBlank(message="Please enter your Cardno")
    @NotNull
    @Column
    private String cardNo;
   
//    @Column(name="email_Address")
//    @NotEmpty(message = "Email name cannot be empty.Please enter the Email")
//    @Email
//    private String emailAddr;
//   
//    @Size(min=10,max=10,message="Please enter valid phone number")
//    @NotBlank(message="Please enter your phone number")
//    @Column
//    private String mobileNo;
   
    @NotNull
    @Positive(message = "Payment amount cannot be negative .Please enter valid amount")
    private double paymentAmount;
   
   
    @Column(name="payFrom")
    private String payFrom="Credit Card Payment";
    
	@ManyToOne
	@JoinColumn(name = "userId", nullable = true)
//		@JsonIgnore
	private User user;

	@OneToOne(targetEntity = Payment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId")
	private Payment payment;

	// Getters and setters

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getTranId() {
		return tranId;
	}

	public void setTranId(long tranId) {
		this.tranId = tranId;
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

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPayFrom() {
		return payFrom;
	}

	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long tranId, String status,
			@NotNull(message = "Please enter the  transaction date in the format(dd/MM/yyyy).") @PastOrPresent LocalDate date,
			@NotNull(message = "Please enter the  transaction time in the format(HH:mm:ss).") LocalTime time,
			@Size(min = 16, max = 16, message = "Please enter valid card number") @NotBlank(message = "Please enter your Cardno") @NotNull String cardNo,
			@NotNull @Positive(message = "Payment amount cannot be negative .Please enter valid amount") double paymentAmount,
			String payFrom, User user, Payment payment) {
		super();
		this.tranId = tranId;
		this.status = status;
		this.date = date;
		this.time = time;
		this.cardNo = cardNo;
		this.paymentAmount = paymentAmount;
		this.payFrom = payFrom;
		this.user = user;
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((payFrom == null) ? 0 : payFrom.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paymentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (int) (tranId ^ (tranId >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Transaction other = (Transaction) obj;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (payFrom == null) {
			if (other.payFrom != null)
				return false;
		} else if (!payFrom.equals(other.payFrom))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (Double.doubleToLongBits(paymentAmount) != Double.doubleToLongBits(other.paymentAmount))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (tranId != other.tranId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [tranId=" + tranId + ", status=" + status + ", date=" + date + ", time=" + time
				+ ", cardNo=" + cardNo + ", paymentAmount=" + paymentAmount + ", payFrom=" + payFrom + ", user=" + user
				+ ", payment=" + payment + "]";
	}

	

}
