package com.askprice.carprice.service.impl;

import java.util.Date;
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
import com.askprice.carprice.dao.CarCityDealerDao;
import com.askprice.carprice.dao.CarDao;
import com.askprice.carprice.dao.CarDealerDao;
import com.askprice.carprice.dao.CarPriceDao;
import com.askprice.carprice.dao.ListPage;
import com.askprice.carprice.dao.PaginationData;
import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.AskPriceRequest;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.SearchRequest;
import com.askprice.carprice.entity.AskRequest;
import com.askprice.carprice.entity.CarCityDealer;
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
	private CarDealerDao carDealerDao;
	
	@Autowired
	private CarCityDealerDao carCityDealerDao;
	
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
		
		if (carId == null || carId.equals("")) return null;
		if (cityId == null || cityId.equals("")) return null;
		
		List<CarDealer> dealerList = carDao.getDealerByCarId_CityId(cityId, Long.parseLong(carId));
		if (dealerList == null || dealerList.size()==0) 
		{
			dealerList = RemoteAPIProxy.getCarDealerByCarId(carId, cityId);
			
			List<CarCityDealer> oldDealers = carCityDealerDao.getDealerByCityIdAndCarId(cityId, Long.parseLong(carId));
			oldDealers.forEach(dealer -> 
			{
				carDealerDao.delete(dealer.getDealerId());
				carCityDealerDao.delete(dealer);
			});
			
			dealerList.forEach(dealer -> 
			{
				CarDealer newDealer = carDealerDao.save(dealer);
				CarCityDealer cityDealer = new CarCityDealer();
				cityDealer.initCityDealer(cityId, Long.parseLong(carId), newDealer.getId());
				carCityDealerDao.save(cityDealer);
			});
		}
		
		return dealerList;
	}

	@Override
	public String sendMessage(String phone) {
		SmsCode smscode = new SmsCode(phone, 2);
		String reqId = UUID.randomUUID().toString().replaceAll("-", "");
		smsSession.put(reqId, smscode);
		System.out.println(smscode.getCode());
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
		
		String reqId = request.getReqId();
//		String errorMessage = VerifySmsCode(reqId, request.getSmscode());
		String errorMessage = "";
		
		if (!errorMessage.equals("")) return errorMessage;
		
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
			req.setSerialId(request.getSerialId());
			req.setZt(request.getZt());
			req.setRequestTime(new Date());
			carPriceDao.save(req);		
		}
		
		return "";
	}
	
	private String VerifySmsCode(String reqId, String actualCode) 
	{
		if (reqId == null || reqId.equals("")) 
		{
			return "短信验证码验证不通过,请重新验证.";
		}
		
		SmsCode code = smsSession.getCode(reqId);
		if (code == null) 
		{
			return "短信验证码验证不通过,请重新验证.";
		}
		
		if (code.isExpired()) 
		{
			return "验证码已过期,请重新验证";
		}
		
		if (code.getCode().equals(actualCode)) 
		{
			return "";
		}
		else 
		{
			return "短信验证码验证不通过,请重新验证.";
		}
	}

	@Override
	public PaginationData<AskPriceRecord> getAskPriceRecord(SearchRequest request) {
		return carDao.getAskpriceRequest(request);
	}

}
