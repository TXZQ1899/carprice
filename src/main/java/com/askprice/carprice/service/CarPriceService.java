package com.askprice.carprice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.CarDealer;
import com.askprice.carprice.entity.CarInfo;

public interface CarPriceService {
	
	public List<CarInfo> getCarBySerialId(Long serialId);
	
	public String getCurrentCityInfo(HttpServletRequest request);
	
	public List<CarInfoDto> getCarInfoBySerialId(Long serialId);
	
	public List<CarDealer> getCarDealerByCarId(String carId, String cityId);
	
	public String sendMessage(String phone);

}
