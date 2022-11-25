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

import com.ductai.dto.ResponeObject;
import com.ductai.dto.UserDTO;
import com.ductai.service.IUserService;


@RestController
@RequestMapping(value ="/api/users")
public class UserAPI {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	public List<UserDTO> getUsers(){
		return this.userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponeObject> getUser(@PathVariable(value = "id") long id){
		UserDTO foundUser = this.userService.findByIdAndStatus(id, true);
		if(foundUser == null) {
			return new ResponseEntity<ResponeObject>(
				new ResponeObject("failed","Cannot find user with id = "+id,""),HttpStatus.NOT_FOUND
			);
		}else {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("ok","Query user successfully",foundUser),HttpStatus.OK
				);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<ResponeObject> addUser(@RequestBody UserDTO user){
		if(this.userService.checkValidEmail(null, user.getEmail()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","User email already taken",""),HttpStatus.NOT_IMPLEMENTED
				);
		}
		this.userService.save(user);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("ok","Insert user successfully",user),HttpStatus.OK
			);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponeObject> updateUser(@PathVariable(value = "id") long id,@RequestBody UserDTO user){
		UserDTO oldUser = this.userService.findByIdAndStatus(id, true);
		if(oldUser == null || this.userService.checkValidEmail(id, user.getEmail()) == false) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot update user",""),HttpStatus.NOT_FOUND
				);
		}
		user.setId(id);
		this.userService.save(user);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("ok","Update user successfully",user),HttpStatus.OK
			);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeObject> deleteUser(@PathVariable(value = "id") long id) {
		UserDTO foundUser = this.userService.findByIdAndStatus(id, true);
		if(foundUser == null) {
			return new ResponseEntity<ResponeObject>(
					new ResponeObject("failed","Cannot find user to delete",""),HttpStatus.NOT_FOUND
				);
		}
		this.userService.delete(id);
		return new ResponseEntity<ResponeObject>(
				new ResponeObject("ok","Delete user successfully",""),HttpStatus.OK
			);
	}
	
}
