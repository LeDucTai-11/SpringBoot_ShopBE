package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.BillDetailConverter;
import com.ductai.dto.BillDetailDTO;
import com.ductai.entity.BillDetail;
import com.ductai.entity.BillEntity;
import com.ductai.entity.ProductEntity;
import com.ductai.repository.BillDetailRepository;
import com.ductai.repository.BillRepository;
import com.ductai.repository.ProductRespository;
import com.ductai.service.IBillDetailService;

@Service
public class BillDetailService implements IBillDetailService {
	
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	@Autowired
	private BillDetailConverter billDetailConverter;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ProductRespository productRepository;

	@Override
	public List<BillDetailDTO> findAll() {
		List<BillDetailDTO> results = new ArrayList<BillDetailDTO>();
		this.billDetailRepository.findByStatus(true).forEach(data -> {
			results.add(this.billDetailConverter.toDTO(data));
		});
		return results;
	}

	@Override
	@Transactional
	public void save(BillDetailDTO billDetail) {
		BillDetail result = new BillDetail();
		BillEntity foundBill = this.billRepository.findByIdAndStatus(billDetail.getBill_id(),true);
		ProductEntity foundProduct = this.productRepository.findByIdAndStatus(billDetail.getProduct_id(),true);
		if(billDetail.getId() == null) {
			result = this.billDetailConverter.toEntity(billDetail);
		}else {
			result = this.billDetailConverter.toEntity(this.billDetailRepository.findOne(billDetail.getId()), billDetail);
		}
		result.setBill(foundBill);
		result.setProduct(foundProduct);
		result.setTotal(billDetail.getAmount() * foundProduct.getPrice());
		result.setStatus(true);
		this.billDetailRepository.save(result);
		foundProduct.setAmount(foundProduct.getAmount() - billDetail.getAmount());
		foundBill.setTotal(foundBill.getTotal() + result.getTotal());
		this.productRepository.save(foundProduct);
		this.billRepository.save(foundBill);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		BillDetail foundBillDetail = this.billDetailRepository.findByIdAndStatus(id, true);
		BillEntity foundBill = this.billRepository.findByIdAndStatus(foundBillDetail.getBill().getId(), true);
		foundBillDetail.setStatus(false);
		foundBill.setTotal(foundBill.getTotal() - foundBillDetail.getTotal());
		this.billDetailRepository.save(foundBillDetail);
		this.billRepository.save(foundBill);
	}

	@Override
	public BillDetailDTO findByIdAndStatus(Long id,boolean status) {
		if(this.billDetailRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.billDetailConverter.toDTO(this.billDetailRepository.findByIdAndStatus(id, status));
	}

	@Override
	public List<BillDetailDTO> findByIDBill(Long id) {
		List<BillDetailDTO> results = new ArrayList<BillDetailDTO>();
		this.billDetailRepository.findByBillAndStatus(this.billRepository.findOne(id), true)
			.forEach(data -> {
				results.add(this.billDetailConverter.toDTO(data));
			});
		return results;
	}
	
	
	
	
}
