package com.askprice.carprice.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.SearchRequest;
import com.askprice.carprice.entity.CarDealer;

public interface CarDao {
	
	public List<CarInfoDto> getCarBySerialId(Long serialId);
	
	public List<CarDealer> getDealerByCarId_CityId(String cityId, Long carId);
	
	public Page<AskPriceRecord> getAskpriceRequest(SearchRequest request);

}
