package com.askprice.carprice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.askprice.carprice.dao.ListPage;
import com.askprice.carprice.dao.PaginationData;
import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.AskPriceRequest;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.SearchRequest;
import com.askprice.carprice.entity.CarDealer;
import com.askprice.carprice.entity.CarInfo;

public interface CarPriceService {
	
	public List<CarInfo> getCarBySerialId(Long serialId);
	
	public String getCurrentCityInfo(HttpServletRequest request);
	
	public List<CarInfoDto> getCarInfoBySerialId(Long serialId);
	
	public List<CarDealer> getCarDealerByCarId(String carId, String cityId);
	
	public String sendMessage(String phone);
	
	public String saveRequest(AskPriceRequest request);
	
	public PaginationData<AskPriceRecord> getAskPriceRecord(SearchRequest request);

}
