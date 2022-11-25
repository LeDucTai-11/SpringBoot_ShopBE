package com.ductai.service;

import java.util.List;

import com.ductai.dto.UserDTO;

public interface IUserService {
	List< UserDTO > getUsers();
	void save(UserDTO user);
	UserDTO findByIdAndStatus(Long id,boolean status);
	void delete(long id);
	boolean checkValidEmail(Long id,String email);
}
