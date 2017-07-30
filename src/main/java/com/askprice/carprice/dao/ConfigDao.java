package com.askprice.carprice.dao;

import org.springframework.data.repository.CrudRepository;

import com.askprice.carprice.entity.Config;

public interface ConfigDao extends CrudRepository<Config, Integer> {
	
	Config findByConfigName(String name);
	
	void delete(Integer id);

}
