package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.BillEntity;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long>{
	
	BillEntity findByIdAndStatus(Long id,boolean status);
	List<BillEntity> findByStatus(boolean status);
}
