package com.askprice.carprice.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.CarInfo;

public interface CarPriceService {
	
	public List<CarInfo> getCarBySerialId(Long serialId);
	
	public Map<String, String> getCurrentCityInfo(HttpServletRequest request);
	
	public List<CarInfoDto> getCarInfoBySerialId(Long serialId);

}
