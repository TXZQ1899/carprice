package com.askprice.carprice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.service.CarPriceService;

@RestController
@RequestMapping("/api/car")
public class CarPriceController {
	
	@Autowired
	private CarPriceService carService;
	
	@RequestMapping(value = {"/list"} , method = RequestMethod.GET)	
	public @ResponseBody List<CarInfo> contactlist(
		@RequestParam(value = "serialId", required = true) Long serialId) 
			 throws Exception
	{
		return carService.getCarBySerialId(serialId);
	}
	
	

}
