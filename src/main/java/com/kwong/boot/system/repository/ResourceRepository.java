package com.kwong.boot.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.kwong.boot.system.model.Resource;

public interface ResourceRepository  extends JpaRepository<Resource, Integer> {

}
