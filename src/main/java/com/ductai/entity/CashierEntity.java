package com.ductai.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cashier")
public class CashierEntity extends BaseEntity{

	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "cashier",cascade = CascadeType.ALL)
	private List<BillEntity> bills = new ArrayList<>();

	
	
	public List<BillEntity> getBills() {
		return bills;
	}

	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
