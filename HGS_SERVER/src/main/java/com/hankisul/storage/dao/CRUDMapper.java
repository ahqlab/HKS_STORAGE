package com.hankisul.storage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hankisul.storage.domain.Domain;
import com.hankisul.storage.domain.DomainParam;

/**
 * Common CRUD Mapper 
 * 
 * @author taeyo
 * 
 */
public interface CRUDMapper<D extends Domain, P extends DomainParam, PK> {
	
	int insert(D domain);
	
	D get(PK id);

	int update(D domain);
	
	int delete(PK id);
	
	int getCountByParam(@Param("param") P param);
	
	List<D> getListByParam(
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize,
			@Param("param") P param);

	List<D> getList();
}

