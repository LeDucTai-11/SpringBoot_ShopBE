package com.ductai.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ductai.dto.UserDTO;
import com.ductai.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserDTO toDTO(UserEntity user) {
		UserDTO result = new UserDTO();
		result.setId(user.getId());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setEmail(user.getEmail());
		result.setCreatedDate((Timestamp)user.getCreatedDate());
		result.setModifiedDate((Timestamp)user.getModifiedDate());
		result.setStatus(user.isStatus());
		return result;
	}
	
	public UserEntity toEntity(UserDTO user) {
		UserEntity result = new UserEntity();
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setEmail(user.getEmail());
		result.setStatus(user.isStatus());
		return result;
	}
	
	public UserEntity toEntity(UserEntity result,UserDTO user) {
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setEmail(user.getEmail());
		result.setStatus(user.isStatus());
		return result;
	}
}
