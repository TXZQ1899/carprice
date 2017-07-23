package com.askprice.carprice.dao;

import java.util.List;

import com.askprice.carprice.dto.CarInfoDto;

public interface CarDao {
	
	public List<CarInfoDto> getCarBySerialId(Long serialId);

}
