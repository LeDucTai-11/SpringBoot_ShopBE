package com.ductai.service;

import java.util.List;

import com.ductai.dto.BillDTO;

public interface IBillService {
	List<BillDTO> findAll();
	BillDTO findByIdAndStatus(Long id,boolean status);
	void save(BillDTO bill);
	void delete(Long id);
	void deleteBillDetailsByBill(Long id);
}
