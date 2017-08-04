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
import com.askprice.carprice.common.util.mail.EmailTools;
import com.askprice.carprice.dao.ListPage;
import com.askprice.carprice.dao.PaginationData;
import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.AskPriceRequest;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.ResponseResult;
import com.askprice.carprice.dto.SearchRequest;
import com.askprice.carprice.entity.CarDealer;
import com.askprice.carprice.entity.CarInfo;
import com.askprice.carprice.entity.MailList;
import com.askprice.carprice.service.CarPriceService;

@RestController
@Scope("prototype")
@RequestMapping("/api/car")
public class CarPriceController {

	@Autowired
	private CarPriceService carService;
	
	@Autowired
	private EmailTools email;

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
	
	@RequestMapping(value = { "/search" }, method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody PaginationData<AskPriceRecord> search(@RequestBody(required = false) SearchRequest request,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "page_size", required = false) Integer page_size,
			@RequestParam(value = "pagetype", required = false) String page_type,
			@RequestParam(value = "page_no", required = false) Integer page_no,
			@RequestParam(value = "appsku", required = false) String appsku,
			@RequestParam(value = "zt", required = false) String zt,
			@RequestParam(value = "pagetype", required = false) String pagetype,
			@RequestParam(value = "channel", required = false) String channel) throws Exception {
		
		if (request==null)
		{
			request = new SearchRequest(brand, province, city, appsku, channel, zt,
					start_time, end_time, page_type, page_size, page_no);
		}
		
		if(request.getPage_size() == null) 
		{
			request.setPage_size(15);
		}
		
		if (request.getPage_no() == null) 
		{
			request.setPage_no(1);
		}
		
		return carService.getAskPriceRecord(request);
	}
	
	@RequestMapping(value = { "/export" }, method = RequestMethod.GET)
	public @ResponseBody String export() throws Exception {
		carService.ExportRecord();
		email.sendEmailWithReport();
		return "success";
	}
	
	@RequestMapping(value = { "/config/sms" }, method = RequestMethod.GET)
	public @ResponseBody String smsconfig() throws Exception {
		return carService.getSmsSwitchValue();
	}
	
	@RequestMapping(value = { "/config/sms" }, method = RequestMethod.PUT)
	public @ResponseBody String updateSmsConfig(@RequestParam(value = "value", required = true) String value) throws Exception {
		carService.updateSmsSwitchValue(value);
		return carService.getSmsSwitchValue();
	}
	
	@RequestMapping(value = { "/config/maillist" }, method = RequestMethod.GET)
	public @ResponseBody List<MailList> mailList() throws Exception {
		List<MailList> list = carService.getMailList();
		return list;
	}
	
	@RequestMapping(value = { "/config/maillist" }, method = RequestMethod.PUT)
	public @ResponseBody String addMailList(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "mail", required = true) String mail) throws Exception {
		carService.addMail(mail, name);
		return "success";
	}
	
	@RequestMapping(value = { "/config/maillist" }, method = RequestMethod.POST)
	public @ResponseBody String deleteMailList(@RequestParam(value = "chk_ids", required = true) String[] mailIds) throws Exception {
		carService.deleteMail(mailIds);
		return "success";
	}
	
	@RequestMapping(value = { "/query/brandlist" }, method = RequestMethod.GET)
	public @ResponseBody List<String> getBrandList() throws Exception {
		return carService.getRequestedBrandList();
	}
	
	@RequestMapping(value = { "/query/channellist" }, method = RequestMethod.GET)
	public @ResponseBody List<String> getChannelList() throws Exception {
		return carService.getRequestedChannelList();
	}
	
	@RequestMapping(value = { "/query/appskulist" }, method = RequestMethod.GET)
	public @ResponseBody List<String> getAppskuList() throws Exception {
		return carService.getRequestedAppskuList();
	}
	
	@RequestMapping(value = { "/query/pagetypelist" }, method = RequestMethod.GET)
	public @ResponseBody List<String> getPagetypeList() throws Exception {
		return carService.getRequestedPageTypeList();
	}
	
	@RequestMapping(value = { "/query/ztlist" }, method = RequestMethod.GET)
	public @ResponseBody List<String> getZtList() throws Exception {
		return carService.getRequestedZtList();
	}
	
//	@RequestMapping(value = { "/saverequest" }, method = RequestMethod.POST)
//	public @ResponseBody String saveRequest(@RequestBody AskPriceRequest request) throws Exception {
//		carService.saveRequest(request);
//		return "您的询价请求已经成功提交!";
//	}

}
