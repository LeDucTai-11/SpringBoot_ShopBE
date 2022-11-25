package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.BillDTO;
import com.ductai.entity.BillEntity;

@Component
public class BillConverter {
	
	public BillDTO toDTO(BillEntity bill) {
		BillDTO result = new BillDTO();
		result.setId(bill.getId());
		result.setCashier_id(bill.getCashier().getId());
		result.setUser_id(bill.getUser().getId());
		result.setTotal(bill.getTotal());
		result.setCreatedDate((Timestamp)bill.getCreatedDate());
		result.setModifiedDate((Timestamp)bill.getModifiedDate());
		result.setStatus(bill.isStatus());
		return result;
	}
	
	public BillEntity toEntity(BillDTO bill) {
		BillEntity result = new BillEntity();
		result.setStatus(bill.isStatus());
		return result;
	}
	
	public BillEntity toEntity(BillEntity result,BillDTO bill) {
		result.setStatus(bill.isStatus());
		return result;
	}
}
