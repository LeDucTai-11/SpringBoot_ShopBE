package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.CashierConverter;
import com.ductai.dto.CashierDTO;
import com.ductai.entity.CashierEntity;
import com.ductai.repository.CashierRepository;
import com.ductai.service.ICashierService;

@Service
public class CashierService implements ICashierService {

	@Autowired
	private CashierRepository cashierRepository;
	
	@Autowired
	private CashierConverter cashierConverter;
	
	public List<CashierDTO> findAll(){
		List<CashierDTO> data = new ArrayList<CashierDTO>();
		this.cashierRepository.findByStatus(true).forEach(cashier -> {
			data.add(this.cashierConverter.toDTO((CashierEntity) cashier));
		});
		return data;
	}

	@Transactional
	public void saveCashier(CashierDTO cashier) {
		CashierEntity result = new CashierEntity();
		if(cashier.getId() == null) {
			result = this.cashierConverter.toEntity(cashier);
		}else {
			result = this.cashierConverter.toEntity(this.cashierRepository.findOne(cashier.getId()), cashier);
		}
		result.setStatus(true);
		this.cashierRepository.save(result);
	}
	
	@Override
	@Transactional
	public void deleteCasshier(Long id) {
		CashierEntity cashier = this.cashierRepository.findByIdAndStatus(id, true);
		cashier.setStatus(false);
		this.cashierRepository.save(cashier);
	}

	@Override
	public CashierDTO finByIdAndStatus(Long id, boolean status) {
		if(this.cashierRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.cashierConverter.toDTO(this.cashierRepository.findByIdAndStatus(id, status));
	}

	@Override
	public boolean checkValidEmail(Long id, String email) {
		CashierEntity cashier = this.cashierRepository.findByEmail(email);
		
		if(cashier == null) return true;
		
		if(id == null) {
			if(cashier != null) return false;
		}else {
			if(cashier.getId() != id) return false;
		}
		return true;
	}

	

	
	
}
