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

import com.ductai.dto.CategoryDTO;
import com.ductai.dto.ResponeObject;
import com.ductai.service.ICategoryService;
import com.ductai.service.IProductService;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryAPI {
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("")
	public List<CategoryDTO> getCategorys() {
		return this.categoryService.getCategorys();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getCategory(@PathVariable (value = "id") long id){
		CategoryDTO foundCategory = this.categoryService.findByIdAndStatus(id, true);
		if(foundCategory == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find category with id = "+id,""),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("ok","Query category successfully",this.productService.getProducts(id)),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addCategory(@RequestBody CategoryDTO category){
		if(this.categoryService.checkValidName(null, category.getName()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Category name already taken",""),HttpStatus.NOT_IMPLEMENTED);
		}
		this.categoryService.save(category);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("ok","Insert category successfully",category),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateCategory(@PathVariable(value = "id") long id,@RequestBody CategoryDTO category){
		CategoryDTO foundCategory = this.categoryService.findByIdAndStatus(id, true);
		if(foundCategory == null || this.categoryService.checkValidName(id, category.getName()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot update category",""),HttpStatus.NOT_FOUND);
		}
		category.setId(id);
		this.categoryService.save(category);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Update category successfully",category),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteCategory(@PathVariable(value = "id") long id){
		CategoryDTO foundCategory = this.categoryService.findByIdAndStatus(id, true);
		if(foundCategory == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find category to delete",""),HttpStatus.NOT_FOUND);
		}
		this.categoryService.delete(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Delete category successfully",""),HttpStatus.OK);
	}
}
