package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.CategoryDTO;
import com.ductai.entity.CategoryEntity;

@Component
public class CategoryConverter {

	public CategoryDTO toDTO(CategoryEntity category) {
		CategoryDTO result = new CategoryDTO();
		result.setId(category.getId());
		result.setName(category.getName());
		result.setCode(category.getCode());
		result.setCreatedDate((Timestamp)category.getCreatedDate());
		result.setModifiedDate((Timestamp)category.getModifiedDate());
		result.setStatus(category.isStatus());
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO category) {
		CategoryEntity result = new CategoryEntity();
		result.setName(category.getName());
		result.setCode(category.getCode());
		result.setStatus(category.isStatus());
		return result;
	}
	
	public CategoryEntity toEntity(CategoryEntity result,CategoryDTO category) {
		result.setName(category.getName());
		result.setCode(category.getCode());
		result.setStatus(category.isStatus());
		return result;
	}
}
