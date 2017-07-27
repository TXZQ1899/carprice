package com.askprice.carprice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.askprice.carprice.common.cache.SmsCode;
import com.askprice.carprice.common.cache.SmsSession;
import com.askprice.carprice.common.message.client.sms.TemplateSMSSender;
import com.askprice.carprice.common.util.IPTools;
import com.askprice.carprice.common.util.RemoteAPIProxy;
import com.askprice.carprice.dao.CarDao;
import com.askprice.carprice.dao.CarPriceDao;
import com.askprice.carprice.dto.AskPriceRequest;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.AskRequest;
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
	private SmsSession smsSession;
	
	@Autowired
	private TemplateSMSSender sender;
	
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

	@Override
	public String sendMessage(String phone) {
		SmsCode smscode = new SmsCode(phone, 2);
		String reqId = UUID.randomUUID().toString().replaceAll("-", "");
		smsSession.put(reqId, smscode);
		
		return reqId;
//		HashMap<String, Object> result = null;
//		String[] message = new String[]{smscode.getCode(), smscode.getExpiredMinutes().toString()};
//		result = sender.getSmsSender().sendTemplateSMS(phone, "159273" ,message);
//		if("000000".equals(result.get("statusCode"))){
//			return reqId;
//		}else{
//			return "-1";
//		}
	}

	@Override
	public String saveRequest(AskPriceRequest request) {
		
		Long[] dealers = request.getDealers();
		for(Long dealer : dealers) 
		{
			AskRequest req = new AskRequest();
			req.setAppsku(request.getAppsku());
			req.setBrand(request.getBrand());
			req.setCarId(request.getCarId());
			req.setChannel(request.getChannel());
			req.setCity(request.getCity());
			req.setDealerId(dealer);
			req.setName(request.getName());
			req.setPagetype(request.getPagetype());
			req.setPhone(request.getPhone());
			req.setProvince(request.getProvince());
//			req.setReqId(request.getReqId());
			req.setSerialId(request.getSerialId());
//			req.setSmscode(code);
			req.setZt(request.getZt());
			req.setRequestTime(new Date());
			carPriceDao.save(req);		
		}
		
		
		
		return "";
	}

}
