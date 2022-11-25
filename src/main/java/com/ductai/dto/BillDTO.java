package com.ductai.dto;

public class BillDTO extends AbstractDTO {
	
	private Long cashier_id;
	private Long user_id;
	private Long total;
	public Long getCashier_id() {
		return cashier_id;
	}
	public void setCashier_id(Long cashier_id) {
		this.cashier_id = cashier_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
