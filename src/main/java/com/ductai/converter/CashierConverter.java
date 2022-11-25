package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.CashierDTO;
import com.ductai.entity.CashierEntity;

@Component
public class CashierConverter {

	public CashierDTO toDTO(CashierEntity cashier) {
		CashierDTO result = new CashierDTO();
		result.setId(cashier.getId());
		result.setName(cashier.getName());
		result.setEmail(cashier.getEmail());
		result.setAddress(cashier.getAddress());
		result.setGender(cashier.isGender());
		result.setCreatedDate((Timestamp)cashier.getCreatedDate());
		result.setModifiedDate((Timestamp)cashier.getModifiedDate());
		result.setStatus(cashier.isStatus());
		return result;
	}
	
	public CashierEntity toEntity(CashierDTO cashier) {
		CashierEntity result = new CashierEntity();
		result.setName(cashier.getName());
		result.setEmail(cashier.getEmail());
		result.setAddress(cashier.getAddress());
		result.setGender(cashier.isGender());
		result.setStatus(cashier.isStatus());
		return result;
	}
	
	public CashierEntity toEntity(CashierEntity result, CashierDTO cashier) {
		result.setName(cashier.getName());
		result.setEmail(cashier.getEmail());
		result.setAddress(cashier.getAddress());
		result.setGender(cashier.isGender());
		result.setStatus(cashier.isStatus());
		return result;
	}
	
}
