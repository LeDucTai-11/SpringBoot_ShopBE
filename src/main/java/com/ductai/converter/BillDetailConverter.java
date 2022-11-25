package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.BillDetailDTO;
import com.ductai.entity.BillDetail;

@Component
public class BillDetailConverter {

	public BillDetailDTO toDTO(BillDetail billDetail) {
		BillDetailDTO result = new BillDetailDTO();
		result.setId(billDetail.getId());
		result.setAmount(billDetail.getAmount());
		result.setTotal(billDetail.getTotal());
		result.setProduct_id(billDetail.getProduct().getId());
		result.setBill_id(billDetail.getBill().getId());
		result.setCreatedDate((Timestamp)billDetail.getCreatedDate());
		result.setModifiedDate((Timestamp)billDetail.getModifiedDate());
		result.setStatus(billDetail.isStatus());
		return result;
	}
	public BillDetail toEntity(BillDetailDTO billDetail) {
		BillDetail result = new BillDetail();
		result.setAmount(billDetail.getAmount());
		result.setStatus(billDetail.isStatus());
		return result;
	}
	public BillDetail toEntity(BillDetail result,BillDetailDTO billDetail) {
		result.setAmount(billDetail.getAmount());
		result.setStatus(billDetail.isStatus());
		return result;
	}
}
