package com.ductai.service;

import java.util.List;

import com.ductai.dto.BillDetailDTO;

public interface IBillDetailService {
	List<BillDetailDTO> findAll();
	List<BillDetailDTO> findByIDBill(Long id);
	void save(BillDetailDTO billDetail);
	void delete(Long id);
	BillDetailDTO findByIdAndStatus(Long id,boolean status);
}
