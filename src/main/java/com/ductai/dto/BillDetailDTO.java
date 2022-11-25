package com.ductai.dto;

public class BillDetailDTO extends AbstractDTO {
	
	private Long product_id;
	private Long bill_id;
	private Long amount;
	private Long total;
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getBill_id() {
		return bill_id;
	}
	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
}
