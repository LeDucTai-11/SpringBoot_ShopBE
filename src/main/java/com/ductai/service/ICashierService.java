package com.ductai.service;

import java.util.List;

import com.ductai.dto.CashierDTO;

public interface ICashierService {
	
	List<CashierDTO> findAll();
	CashierDTO finByIdAndStatus(Long id,boolean status);
	boolean checkValidEmail(Long id,String email);
	void saveCashier(CashierDTO cashier);
	void deleteCasshier(Long id);
}
