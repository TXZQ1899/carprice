package com.askprice.carprice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.askprice.carprice.common.util.City;
import com.askprice.carprice.dao.ListPage;
import com.askprice.carprice.dao.PaginationData;
import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.AskPriceRequest;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.ResponseResult;
import com.askprice.carprice.dto.SearchRequest;
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
	
	@RequestMapping(value = { "/sms" }, method = RequestMethod.GET)
	public @ResponseBody Map<String,String> sendSms(
			@RequestParam(value = "phone", required = true) String phone) throws Exception {
		String reqId = carService.sendMessage(phone);
		Map<String, String> returnValue = new HashMap<>();
		if (reqId.equals("-1")) 
		{
			returnValue.put("code", "-1");
			returnValue.put("message", "短信发送失败,请重新获取!");
		}
		else 
		{
			returnValue.put("code", "0");
			returnValue.put("message", "短信发送成功");
			returnValue.put("reqId", reqId);
		}
		return returnValue;
	}

	private ResponseResult initResult(Object data) {
		ResponseResult result = new ResponseResult();
		result.setCode("0");
		result.setMessage("success");
		result.setData(data);
		return result;
	}
	
	private ResponseResult initResult(String message) {
		ResponseResult result = new ResponseResult();
		result.setCode("0");
		result.setMessage(message);
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
				returnValue = city.getProvince()+ "," + cityName + "," + city.getCode();
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
	
	@RequestMapping(value = { "/saverequest" }, method = RequestMethod.POST)
	public @ResponseBody ResponseResult saveRequest(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "reqId", required = true) String reqId,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "brand", required = true) String brand,
			@RequestParam(value = "serialId", required = true) Long serialId,
			@RequestParam(value = "carId", required = true) Long carId,
			@RequestParam(value = "dealers", required = true) String dealers,
			@RequestParam(value = "appsku", required = true) String appsku,
			@RequestParam(value = "zt", required = true) String zt,
			@RequestParam(value = "pageType", required = true) String pagetype,
			@RequestParam(value = "channel", required = true) String channel) throws Exception {
		
		AskPriceRequest request = new AskPriceRequest();
		request.setAppsku(appsku);
		request.setBrand(brand);
		request.setCarId(carId);
		request.setChannel(channel);
		request.setCity(city);
		String[] strArray = dealers.split(",");
		Long[] delearIds = new Long[strArray.length];
		int index = 0;
		for(String str : strArray) 
		{
			delearIds[index] = Long.parseLong(str);
			index++;
		}
		request.setDealers(delearIds);
		request.setName(name);
		request.setPagetype(pagetype);
		request.setPhone(phone);
		request.setProvince(province);
		request.setReqId(reqId);
		request.setSerialId(serialId);
		request.setSmscode(code);
		request.setZt(zt);
		
		String errorMessage = carService.saveRequest(request);
		if (!errorMessage.equals("")) return initResult(errorMessage);
		
		return initResult("您的询价请求已经成功提交!");
	}
	
	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public @ResponseBody PaginationData<AskPriceRecord> search(@RequestBody SearchRequest request) throws Exception {
		
		System.out.println(request.getBrand());
		request.setPageSize(15);
		request.setPageNo(0);
		return carService.getAskPriceRecord(request);
	}
	
//	@RequestMapping(value = { "/saverequest" }, method = RequestMethod.POST)
//	public @ResponseBody String saveRequest(@RequestBody AskPriceRequest request) throws Exception {
//		carService.saveRequest(request);
//		return "您的询价请求已经成功提交!";
//	}

}
