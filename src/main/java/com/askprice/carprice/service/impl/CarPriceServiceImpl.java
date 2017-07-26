package com.askprice.carprice.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.askprice.carprice.common.util.IPTools;
import com.askprice.carprice.common.util.RemoteAPIProxy;
import com.askprice.carprice.dao.CarDao;
import com.askprice.carprice.dao.CarPriceDao;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.CarDealer;
import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.service.CarPriceService;

@Service
@Scope("prototype")
public class CarPriceServiceImpl implements CarPriceService {
	
	public Logger logger = LoggerFactory.getLogger(CarPriceServiceImpl.class);
	
	@Autowired
	private CarPriceDao carPriceDao;
	
	@Autowired
	private CarDao carDao;

	@Override
	@Transactional
	public List<CarInfo> getCarBySerialId(Long serialId) {

		List<CarInfo> carList = carPriceDao.findByCarSerialId(serialId);
		return carList;
	}

	@Override
	public String getCurrentCityInfo(HttpServletRequest request) {
		String ipAddr = IPTools.getIpAddress(request);
		logger.info("get request from IP address : " + ipAddr);
		return IPTools.getCity(ipAddr).get("city");
	}

	@Override
	@Transactional
	public List<CarInfoDto> getCarInfoBySerialId(Long serialId) {
		return carDao.getCarBySerialId(serialId);
	}

	@Override
	public List<CarDealer> getCarDealerByCarId(String carId, String cityId) {
		return RemoteAPIProxy.getCarDealerByCarId(carId, cityId);
	}

}
