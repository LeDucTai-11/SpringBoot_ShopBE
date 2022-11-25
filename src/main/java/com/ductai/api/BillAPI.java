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
import com.ductai.dto.CashierDTO;
import com.ductai.dto.ResponeObject;
import com.ductai.dto.UserDTO;
import com.ductai.service.IBillDetailService;
import com.ductai.service.IBillService;
import com.ductai.service.ICashierService;
import com.ductai.service.IUserService;

@RestController
@RequestMapping(value = "/api/bill")
public class BillAPI {
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IBillDetailService billDetailService;
	
	@Autowired
	private ICashierService cashierService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	public List<BillDTO> getAllBills() {
		return this.billService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getBillByID(@PathVariable(value = "id")Long id){
		BillDTO foundBill = this.billService.findByIdAndStatus(id, true);
		if(foundBill == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find Bill with ID: "+id,""),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Query Bill successfully",this.billDetailService.findByIDBill(id)),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addBill(@RequestBody BillDTO bill) {
		CashierDTO foundCashier = this.cashierService.finByIdAndStatus(bill.getCashier_id(), true);
		UserDTO foundUser = this.userService.findByIdAndStatus(bill.getUser_id(),true);
		if(foundCashier == null || foundUser == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot insert bill",""),HttpStatus.NOT_IMPLEMENTED);
		}
		this.billService.save(bill);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Inseret Bill successfully",bill),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateBill(@RequestBody BillDTO bill) {
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("failed","Cannot update Bill",""),HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteBill(@PathVariable(value = "id")Long id){
		BillDTO foundBill = this.billService.findByIdAndStatus(id, true);
		if(foundBill == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find Bill with ID = "+id,""),HttpStatus.NOT_FOUND);
		}
		this.billService.delete(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Delete Bill successfully",""),HttpStatus.OK);
	}
}
