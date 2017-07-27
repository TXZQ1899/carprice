package com.askprice.carprice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.entity.CarDealer;

@Repository
public class CarDaoImpl extends CommonDao implements CarDao  {
	
	@SuppressWarnings("unchecked")
	public List<CarInfoDto> getCarBySerialId(Long serialId) {
		// TODO Auto-generated method stub
		
		String sql = "select \r\n" + 
				"    i.car_id as carId, \r\n" + 
				"    i.cs_id as serialId, \r\n" + 
				"    i.car_name as carName, \r\n" + 
				"    i.car_year as carYear, \r\n" + 
				"    s.cs_show_name as showName, \r\n" + 
				"    s.mb_name as brandName \r\n" + 
				"from\r\n" + 
				"    car_info i \r\n" + 
				"        LEFT JOIN \r\n" + 
				"    car_serial s ON i.cs_id = s.cs_id \r\n" + 
				"where \r\n" + 
				"    s.cs_id = :serialId \r\n" + 
				"order by car_year desc;  ";
		List<CarInfoDto> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("serialId", serialId);
		list = (List<CarInfoDto>) queryListEntity(sql, params, CarInfoDto.class);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarDealer> getDealerByCarId_CityId(String cityId, Long carId) {
		String sql = "select \r\n" + 
				"    d.* \r\n" + 
				"from \r\n" + 
				"    car_dealer d \r\n" + 
				"        left join \r\n" + 
				"    car_city_dealer c ON d.Id = c.dealerId \r\n" + 
				"where \r\n" + 
				"    c.cityId = :cityId and c.carId = :carId and c.updateTime >= DATE_SUB(NOW(), INTERVAL 7 DAY);";
		
		List<CarDealer> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("cityId", cityId);
		params.put("carId", carId);
		
		list = (List<CarDealer>) queryListEntity(sql, params, CarDealer.class);
		return list;
	}

}
