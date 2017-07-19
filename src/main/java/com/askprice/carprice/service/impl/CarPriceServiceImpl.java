package com.askprice.carprice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askprice.carprice.dao.CarPriceDao;
import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.service.CarPriceService;

@Service
public class CarPriceServiceImpl implements CarPriceService {
	
	@Autowired
	private CarPriceDao carPriceDao;

	@Override
	public List<CarInfo> getCarBySerialId(Long serialId) {

		List<CarInfo> carList = carPriceDao.findByCarSerialId(serialId);
		return carList;
	}

}
