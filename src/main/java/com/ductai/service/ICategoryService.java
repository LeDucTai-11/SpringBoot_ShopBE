package com.ductai.service;

import java.util.List;

import com.ductai.dto.CategoryDTO;

public interface ICategoryService {
	List<CategoryDTO> getCategorys();
	void save(CategoryDTO category);
	void delete(Long id);
	CategoryDTO findByIdAndStatus(Long id,boolean status);
	boolean checkValidName(Long id,String name);
	void deleteProductsByCategory(long idCategory);
}
