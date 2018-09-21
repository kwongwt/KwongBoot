package com.kwong.boot.system.repository;

import com.kwong.boot.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface  UserRepository extends JpaRepository<User, Long> {
	
	/**
     * 通过账号获取用户
     */
	User getByUsername(@Param("username") String username);
}
