package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.UserConverter;
import com.ductai.dto.UserDTO;
import com.ductai.entity.UserEntity;
import com.ductai.repository.UserRepository;
import com.ductai.service.IUserService;


@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public List< UserDTO > getUsers() {
		List<UserDTO> result = new ArrayList<UserDTO>();
		for(UserEntity user : this.userRepository.findByStatus(true)) {
			result.add(this.userConverter.toDTO(user));
		}
		return result;
	}
	
	@Transactional
	public void save(UserDTO user) {
		UserEntity data = new UserEntity();
		if(user.getId() == null) {
			data = this.userConverter.toEntity(user);
		}else {
			data = this.userConverter.toEntity(this.userRepository.findOne(user.getId()), user);
		}
		data.setStatus(true);
		this.userRepository.save(data);
	}
	
	@Transactional
	public void delete(long id) {
		UserEntity user = this.userRepository.findOne(id);
		user.setStatus(false);
		this.userRepository.save(user);
	}

	@Override
	public UserDTO findByIdAndStatus(Long id, boolean status) {
		if(this.userRepository.findByIdAndStatus(id, status) == null) {
			return null;
		}
		return this.userConverter.toDTO(this.userRepository.findByIdAndStatus(id, status));
	}
	@Override
	public boolean checkValidEmail(Long id,String email) {
		UserEntity user = this.userRepository.findByEmail(email);
		if(user == null) return true;
		
		if(id == null) { //isCreatingUser
			if(user != null) return false;
		}else {
			if(user.getId() != id) return false;
		}
		return true;
	}
}
