package com.askprice.carprice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.askprice.carprice.entity.AskRequest;

public interface AskPriceRequestDao extends CrudRepository<AskRequest, Long> {

	@Query("SELECT distinct(r.brand) from AskRequest r")
	List<String> getAllBrand();
	
	@Query("SELECT distinct(r.zt) from AskRequest r")
	List<String> getAllSubject();
	
	@Query("SELECT distinct(r.channel) from AskRequest r")
	List<String> getAllChannel();
	
	@Query("SELECT distinct(r.appsku) from AskRequest r")
	List<String> getAllAppsku();
	
	@Query("SELECT distinct(r.pagetype) from AskRequest r")
	List<String> getAllPageType();




}
