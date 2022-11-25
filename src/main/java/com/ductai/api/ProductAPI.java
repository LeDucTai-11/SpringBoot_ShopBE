package com.ductai.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ductai.dto.ProductDTO;
import com.ductai.dto.ResponeObject;
import com.ductai.service.IProductService;

@RestController
@RequestMapping(value = "/api/product")
public class ProductAPI {

	@Autowired
	private IProductService productService;
	
	
	@GetMapping("")
	public List<ProductDTO> getProducts(){
		return this.productService.getProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getProduct(@PathVariable (value = "id") long id) {
		ProductDTO foundProduct = this.productService.findByIdAndStatus(id, true);
		if(foundProduct == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find product with id = "+id,""),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Query product successfully",foundProduct),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addProduct(@RequestBody ProductDTO product){
		if(this.productService.checkValidName(null, product.getName()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Product name already taken",""),HttpStatus.NOT_IMPLEMENTED);
		}
		this.productService.save(product);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Insert product successfully",product),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateProduct(@PathVariable(value = "id") long id,
						@RequestBody ProductDTO product){
		ProductDTO foundProduct = this.productService.findByIdAndStatus(id, true);
		if(foundProduct == null || this.productService.checkValidName(id, product.getName()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot update product",""),HttpStatus.NOT_FOUND);
		}
		product.setId(id);
		this.productService.save(product);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Update product successfully",product),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteProduct(@PathVariable(value = "id") long id){
		ProductDTO foundProduct = this.productService.findByIdAndStatus(id, true);
		if(foundProduct == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find product to delete",""),HttpStatus.NOT_FOUND);
		}
		this.productService.delete(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Delete product successfully",""),HttpStatus.OK);
	}
}
