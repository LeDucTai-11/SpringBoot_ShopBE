package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findByName(String name);
	CategoryEntity findByIdAndStatus(Long id,boolean status);
	List<CategoryEntity> findByStatus(boolean status);
}
