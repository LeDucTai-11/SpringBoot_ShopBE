package com.ductai.service;

import java.util.List;

import com.ductai.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> getProducts();
	List<ProductDTO> getProducts(long idCategory);
	ProductDTO findByIdAndStatus(long id,boolean status);
	boolean checkValidName(Long id,String name);
	void save(ProductDTO product);
	void delete(long id);
}
