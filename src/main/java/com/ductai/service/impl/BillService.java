package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.BillConverter;
import com.ductai.dto.BillDTO;
import com.ductai.entity.BillDetail;
import com.ductai.entity.BillEntity;
import com.ductai.repository.BillDetailRepository;
import com.ductai.repository.BillRepository;
import com.ductai.repository.CashierRepository;
import com.ductai.repository.UserRepository;
import com.ductai.service.IBillService;

@Service
public class BillService implements IBillService {
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillConverter billConverter;
	
	@Autowired 
	private CashierRepository cashierRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	@Override
	public BillDTO findByIdAndStatus(Long id, boolean status) {
		if(this.billRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.billConverter.toDTO(this.billRepository.findByIdAndStatus(id, status));
	}

	@Override
	public List<BillDTO> findAll() {
		List<BillDTO> results = new ArrayList<BillDTO>();
		this.billRepository.findByStatus(true).forEach(data -> {
			results.add(this.billConverter.toDTO(data));
		});
		return results;
	}

	@Override
	@Transactional
	public void save(BillDTO bill) {
		BillEntity result = new BillEntity();
		if(bill.getId() == null) {
			result = this.billConverter.toEntity(bill);
			result.setTotal(0);
		}else {
			result = this.billConverter.toEntity(this.billRepository.findByIdAndStatus(bill.getId(), true), bill);
			result.setTotal(bill.getTotal());
		}
		result.setCashier(this.cashierRepository.findByIdAndStatus(bill.getCashier_id(),true));
		result.setUser(this.userRepository.findByIdAndStatus(bill.getUser_id(),true));
		this.billRepository.save(result);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		BillEntity foundBill = this.billRepository.findByIdAndStatus(id, true);
		deleteBillDetailsByBill(id);
		foundBill.setStatus(false);
		this.billRepository.save(foundBill);
	}

	@Override
	@Transactional
	public void deleteBillDetailsByBill(Long id) {
		for(BillDetail entity : 
			this.billDetailRepository.findByBillAndStatus(this.billRepository.findByIdAndStatus(id, true), true)) {
			entity.setStatus(false);
			this.billDetailRepository.save(entity);
		}
		
	}
	
}
