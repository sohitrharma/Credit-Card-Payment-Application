package com.cg.creditcard.bean;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Statement")
public class Statement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "statementId")
	private Long statementId;

	@Column(name = "dueAmount")
	private double dueAmount;

	@Column(name = "billingDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate billingDate;

	@Column(name = "dueDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dueDate;

//	@Column(name="type")
//	private String type;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = true)
	private User user;

	public Statement() {
		super();
	}

	public Statement(Long statementId, double dueAmount, LocalDate billingDate, LocalDate dueDate, User user) {
		super();
		this.statementId = statementId;
		this.dueAmount = dueAmount;
		this.billingDate = billingDate;
		this.dueDate = dueDate;
		this.user = user;
	}

	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
