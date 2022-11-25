package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.BillDetail;
import com.ductai.entity.BillEntity;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
	
	List<BillDetail> findByStatus(boolean status);
	BillDetail findByIdAndStatus(Long id,boolean status);
	List<BillDetail> findByBillAndStatus(BillEntity bill,boolean status);
}
