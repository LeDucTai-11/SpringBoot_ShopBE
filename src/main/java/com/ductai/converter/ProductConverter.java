package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.ProductDTO;
import com.ductai.entity.ProductEntity;

@Component
public class ProductConverter {

	public ProductDTO toDTO(ProductEntity product) {
		ProductDTO result = new ProductDTO();
		result.setId(product.getId());
		result.setName(product.getName());
		result.setCode(product.getCode());
		result.setCategory_id(product.getCategory().getId());
		result.setPrice(product.getPrice());
		result.setAmount(product.getAmount());
		result.setCreatedDate((Timestamp)product.getCreatedDate());
		result.setModifiedDate((Timestamp)product.getModifiedDate());
		result.setStatus(product.isStatus());
		return result;
	}
	
	public ProductEntity toEntity(ProductDTO product) {
		ProductEntity result = new ProductEntity();
		result.setName(product.getName());
		result.setCode(product.getCode());
		result.setPrice(product.getPrice());
		result.setAmount(product.getAmount());
		result.setStatus(product.isStatus());
		return result;
	}
	public ProductEntity toEntity(ProductEntity result,ProductDTO product) {
		result.setName(product.getName());
		result.setCode(product.getCode());
		result.setPrice(product.getPrice());
		result.setAmount(product.getAmount());
		result.setStatus(product.isStatus());
		return result;
	}
}
