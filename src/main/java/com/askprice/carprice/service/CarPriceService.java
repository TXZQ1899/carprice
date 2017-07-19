package com.askprice.carprice.service;

import java.util.List;

import com.askprice.carprice.entity.CarInfo;

public interface CarPriceService {
	
	public List<CarInfo> getCarBySerialId(Long serialId);

}
