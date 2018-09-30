package com.kwong.boot.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.kwong.boot.system.model.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer> {

	/** 根据Id获取名称
	 * @param roleId
	 * @return
	 */
	String getNameById(@Param("Id") Integer roleId);
}
