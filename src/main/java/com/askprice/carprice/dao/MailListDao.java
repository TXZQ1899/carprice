package com.askprice.carprice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.askprice.carprice.entity.MailList;

public interface MailListDao extends CrudRepository<MailList, Long> {
	
	MailList findById(Long Id);
	
	void delete(Long id);
	
	@Query("SELECT m from MailList m ")
	List<MailList> getAllMails();

}
