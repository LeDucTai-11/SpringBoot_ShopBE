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

import com.ductai.dto.CashierDTO;
import com.ductai.dto.ResponeObject;
import com.ductai.service.ICashierService;

@RestController
@RequestMapping(value = "/api/cashier")
public class CashierAPI {
	
	@Autowired
	private ICashierService cashierService;
	
	@GetMapping("")
	public List<CashierDTO> findAll(){
		return this.cashierService.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getCashiers(@PathVariable(value = "id") long id) {
		CashierDTO foundCashier = this.cashierService.finByIdAndStatus(id, true);
		if(foundCashier == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find cashier with id = "+id,""),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Query cashier successfully ",foundCashier),HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addCashier(@RequestBody CashierDTO cashier){
		if(this.cashierService.checkValidEmail(null, cashier.getEmail()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cashier email already taken",""),HttpStatus.NOT_IMPLEMENTED);
		}
		this.cashierService.saveCashier(cashier);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Insert Cashier successfully",cashier),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateCashier(@PathVariable(value = "id") Long id,@RequestBody CashierDTO cashier){
		CashierDTO foundCashier = this.cashierService.finByIdAndStatus(id, true);
		if(foundCashier == null || this.cashierService.checkValidEmail(id, cashier.getEmail()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot update cashier",""),HttpStatus.NOT_IMPLEMENTED);
		}
		cashier.setId(id);
		this.cashierService.saveCashier(cashier);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Update cashier successfully",cashier),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteCashier(@PathVariable(value = "id") Long id) {
		CashierDTO foundCashier = this.cashierService.finByIdAndStatus(id, true);
		if(foundCashier == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find cashier to delete with ID: "+id,""),HttpStatus.NOT_FOUND);
		}
		this.cashierService.deleteCasshier(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("OK","Delete cashier successfully",""),HttpStatus.OK);
	}
}
