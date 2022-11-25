package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.CategoryEntity;
import com.ductai.entity.ProductEntity;

@Repository
public interface ProductRespository extends JpaRepository<ProductEntity, Long> {
	 List<ProductEntity> findByCategoryAndStatus(CategoryEntity category,boolean status);
	 ProductEntity findByIdAndStatus(Long id,boolean status);
	 ProductEntity findByName(String name);
	 List<ProductEntity> findByStatus(boolean status);
}
