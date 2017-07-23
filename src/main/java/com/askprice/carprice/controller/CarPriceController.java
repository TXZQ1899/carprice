package com.askprice.carprice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.ResponseResult;
import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.service.CarPriceService;

@RestController
@Scope("prototype")
@RequestMapping("/api/car")
public class CarPriceController {
	
	@Autowired
	private CarPriceService carService;
	
	@RequestMapping(value = {"/list"} , method = RequestMethod.GET)	
	public @ResponseBody ResponseResult carListBySerial(
		@RequestParam(value = "serialId", required = true) Long serialId) 
			 throws Exception
	{
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
	
	@RequestMapping(value = {"/currentCity"} , method = RequestMethod.GET)	
	public @ResponseBody ResponseResult currentCity(HttpServletRequest request) 
			 throws Exception
	{
		Map<String,String> cityInfo = carService.getCurrentCityInfo(request);
		ResponseResult result = initResult(cityInfo);
		return result;
	}
	
	@RequestMapping(value = {"/carlist"} , method = RequestMethod.GET)	
	public @ResponseBody ResponseResult carInfoListBySerial(
		@RequestParam(value = "serialId", required = true) Long serialId) 
			 throws Exception
	{
		List<CarInfoDto> carList = carService.getCarInfoBySerialId(serialId);
		ResponseResult result = initResult(carList);
		return result;
	}

}
