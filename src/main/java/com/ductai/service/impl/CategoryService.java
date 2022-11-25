package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.CategoryConverter;
import com.ductai.dto.CategoryDTO;
import com.ductai.entity.CategoryEntity;
import com.ductai.entity.ProductEntity;
import com.ductai.repository.CategoryRepository;
import com.ductai.repository.ProductRespository;
import com.ductai.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Autowired
	private ProductRespository productRepository;
	
	public List<CategoryDTO> getCategorys() {
		List<CategoryDTO> data = new ArrayList<CategoryDTO>();
		for(CategoryEntity category : this.categoryRepository.findByStatus(true)) {
			data.add(this.categoryConverter.toDTO(category));
		}
		return data;
	}
	
	@Transactional
	public void save(CategoryDTO category) {
		CategoryEntity data = new CategoryEntity();
		if(category.getId() == null) {
			data = this.categoryConverter.toEntity(category);
		}else {
			data = this.categoryConverter.toEntity(this.categoryRepository.findOne(category.getId()),category);
		}
		data.setStatus(true);
		this.categoryRepository.save(data);
	}
	
	@Transactional
	public void delete(Long id) {
		CategoryEntity category = this.categoryRepository.findOne(id);
		deleteProductsByCategory(id);
		category.setStatus(false);
		this.categoryRepository.save(category);
	}
	
	@Transactional
	public void deleteProductsByCategory(long idCategory) {
		for(ProductEntity product : 
			this.productRepository.findByCategoryAndStatus(this.categoryRepository.findOne(idCategory), true)) {
			product.setStatus(false);
			this.productRepository.save(product);
		}
	}
	
	
	@Override
	public CategoryDTO findByIdAndStatus(Long id, boolean status) {
		if(this.categoryRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.categoryConverter.toDTO(this.categoryRepository.findByIdAndStatus(id, status));
	}
	@Override
	public boolean checkValidName(Long id, String name) {
		CategoryEntity category = this.categoryRepository.findByName(name);
		
		if(category == null) return true;
		
		if(id == null) {
			if(category != null) return false;
		}else {
			if(category.getId() != id) return false;
		}
		return true;
	}
}
