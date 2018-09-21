package com.kwong.boot.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ResourceRepository  extends JpaRepository<ResourceRepository, Integer> {

	/**
	 * 获取资源url
	 * @param roleId
	 * @return
	 */
	List<String> getResUrlsByRoleId(@Param("roleId") Integer roleId);
}
