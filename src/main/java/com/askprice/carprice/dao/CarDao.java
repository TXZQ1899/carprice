package com.askprice.carprice.dao;

import java.util.List;

import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.CarDealer;

public interface CarDao {
	
	public List<CarInfoDto> getCarBySerialId(Long serialId);
	
	public List<CarDealer> getDealerByCarId_CityId(String cityId, Long carId);

}
