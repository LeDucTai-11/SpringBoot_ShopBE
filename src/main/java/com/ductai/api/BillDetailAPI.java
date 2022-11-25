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

import com.ductai.dto.BillDTO;
import com.ductai.dto.BillDetailDTO;
import com.ductai.dto.ProductDTO;
import com.ductai.dto.ResponeObject;
import com.ductai.service.IBillDetailService;
import com.ductai.service.IBillService;
import com.ductai.service.IProductService;

@RestController
@RequestMapping(value = "/api/billDetail")
public class BillDetailAPI {
	
	@Autowired
	private IBillDetailService billDetailService;
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("")
	public List<BillDetailDTO> getBillDetails() {
		return this.billDetailService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getBillDetailByID(@PathVariable(value = "id")Long id) {
		BillDetailDTO foundBillDetail = this.billDetailService.findByIdAndStatus(id, true);
		if(foundBillDetail == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find BillDetail with ID: "+id,""),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Query BillDetail successfully",foundBillDetail),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addBillDetail(@RequestBody BillDetailDTO billDetail) {
		BillDTO foundBill = this.billService.findByIdAndStatus(billDetail.getBill_id(), true);
		ProductDTO foundProduct = this.productService.findByIdAndStatus(billDetail.getProduct_id(), true);
		if(foundBill == null || foundProduct == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot insert BillDetail",""),HttpStatus.NOT_IMPLEMENTED);
		}
		this.billDetailService.save(billDetail);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Insert BillDetail successfully",billDetail),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateBillDetail(@PathVariable(value = "id") Long id,
			@RequestBody BillDetailDTO billDetail) {
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("failed","Cannot update BillDetai",""),HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteBillDetail(@PathVariable(value = "id")Long id) {
		BillDetailDTO foundBillDetail = this.billDetailService.findByIdAndStatus(id, true);
		if(foundBillDetail == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find BillDetail with ID : "+id,""),HttpStatus.NOT_FOUND);
		}
		this.billDetailService.delete(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Delete BillDetail successfully",""),HttpStatus.OK);
	}
}
