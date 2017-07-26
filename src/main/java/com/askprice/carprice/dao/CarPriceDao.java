package com.askprice.carprice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.askprice.carprice.entity.AskRequest;
import com.askprice.carprice.entity.CarInfo;

public interface CarPriceDao extends CrudRepository<CarInfo, Long> {
	
	 List<CarInfo> findByCarSerialId(Long serialId);

	void save(AskRequest req);
	 
}
