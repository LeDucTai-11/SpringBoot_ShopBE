package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.ProductConverter;
import com.ductai.dto.ProductDTO;
import com.ductai.entity.ProductEntity;
import com.ductai.repository.CategoryRepository;
import com.ductai.repository.ProductRespository;
import com.ductai.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRespository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	public List<ProductDTO> getProducts() {
		List<ProductDTO> data = new ArrayList<ProductDTO>();
		for(ProductEntity product : this.productRepository.findByStatus(true)) {
			data.add(this.productConverter.toDTO(product));
		}
		return data;
	}
	public List<ProductDTO> getProducts(long idCategory) {
		List<ProductDTO> data = new ArrayList<ProductDTO>();
		for(ProductEntity product : 
			this.productRepository.findByCategoryAndStatus(this.categoryRepository.findOne(idCategory), true)) {
			data.add(this.productConverter.toDTO(product));
		}
		return data;
	}
	
	@Transactional
	public void save(ProductDTO product) {
		ProductEntity result = new ProductEntity();
		if(product.getId() == null) {
			result = this.productConverter.toEntity(product);
		}else {
			result = this.productConverter.toEntity(this.productRepository.findOne(product.getId()),product);
		}
		result.setCategory(this.categoryRepository.findOne(product.getCategory_id()));
		result.setStatus(true);
		this.productRepository.save(result);
	}
	
	@Transactional
	public void delete(long id) {
		ProductEntity foundProduct = this.productRepository.findOne(id);
		foundProduct.setStatus(false);
		this.productRepository.save(foundProduct);
	}
	
	@Override
	public ProductDTO findByIdAndStatus(long id, boolean status) {
		if(this.productRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.productConverter.toDTO(this.productRepository.findByIdAndStatus(id, status));
	}
	@Override
	public boolean checkValidName(Long id, String name) {
		ProductEntity product = this.productRepository.findByName(name);
		if(product == null) return true;
		
		if(id == null) {
			if(product != null) return false;
		}else {
			if(product.getId() != id) return false;
		}
		return true;
	}

}
