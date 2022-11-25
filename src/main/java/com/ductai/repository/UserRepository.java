package com.ductai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ductai.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	List<UserEntity> findByStatus(boolean status);
	UserEntity findByIdAndStatus(Long id,boolean status);
}
