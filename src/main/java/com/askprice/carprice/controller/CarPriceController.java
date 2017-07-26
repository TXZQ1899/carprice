package com.askprice.carprice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.askprice.carprice.common.util.City;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.ResponseResult;
import com.askprice.carprice.entity.CarDealer;
import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.service.CarPriceService;

@RestController
@Scope("prototype")
@RequestMapping("/api/car")
public class CarPriceController {

	@Autowired
	private CarPriceService carService;

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public @ResponseBody ResponseResult carListBySerial(
			@RequestParam(value = "serialId", required = true) Long serialId) throws Exception {
		List<CarInfo> carList = carService.getCarBySerialId(serialId);
		ResponseResult result = initResult(carList);
		return result;
	}

	private ResponseResult initResult(Object data) {
		ResponseResult result = new ResponseResult();
		result.setCode("0");
		result.setMessage("success");
		result.setData(data);
		return result;
	}

	@RequestMapping(value = { "/locatecity" }, method = RequestMethod.GET)
	public @ResponseBody String currentCity(HttpServletRequest request) throws Exception {
		String cityName = carService.getCurrentCityInfo(request);
		String returnValue = "";
		City city = null;
		if (cityName != null && cityName.trim().length() > 0) {
			try {
				city = City.valueOf(cityName);
				returnValue = cityName + "," + city.getCode();
			} catch (Exception e) {
			}
		}
		return returnValue;
	}

	@RequestMapping(value = { "/carlist" }, method = RequestMethod.GET)
	public @ResponseBody List<CarInfoDto> carInfoListBySerial(
			@RequestParam(value = "serialid", required = true) Long serialId) throws Exception {
		List<CarInfoDto> carList = carService.getCarInfoBySerialId(serialId);
		return carList;
	}

	@RequestMapping(value = { "/cardealerlist" }, method = RequestMethod.POST)
	public @ResponseBody List<CarDealer> carDealerListByCarId(
			@RequestParam(value = "carid", required = true) String carId,
			@RequestParam(value = "cityid", required = true) String cityId) throws Exception {
		List<CarDealer> carList = carService.getCarDealerByCarId(carId, cityId);
		return carList;
	}

}
