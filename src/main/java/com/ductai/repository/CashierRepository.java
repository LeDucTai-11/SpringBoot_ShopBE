package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.CashierEntity;

@Repository
public interface CashierRepository extends JpaRepository<CashierEntity, Long> {
	List<CashierEntity> findByStatus(boolean status);
	CashierEntity findByIdAndStatus(Long id,boolean status);
	CashierEntity findByEmail(String email);
}
