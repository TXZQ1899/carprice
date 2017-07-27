package com.askprice.carprice.dao;

import org.springframework.data.repository.CrudRepository;

import com.askprice.carprice.entity.CarDealer;

public interface CarDealerDao extends CrudRepository<CarDealer, Long> {
	
	CarDealer findByDealerId(Long dealerId);
	
	void delete(Long dealerId);

}
