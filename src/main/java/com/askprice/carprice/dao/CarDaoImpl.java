package com.askprice.carprice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.askprice.carprice.dto.AskPriceRecord;
import com.askprice.carprice.dto.CarInfoDto;
import com.askprice.carprice.dto.SearchRequest;
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

	@Override
	public PaginationData<AskPriceRecord> getAskpriceRequest(SearchRequest request) {
		ListPage<AskPriceRecord> listPage = new ListPage<>(request.getPageSize(), request.getPageNo());
		String sql = getSQLBySearchRequest(request, false);
		String countSql = getSQLBySearchRequest(request, true);
		listBySqlDto(listPage, countSql, sql, AskPriceRecord.class);
		return buildPagination(request.getPageSize(), request.getPageNo(), listPage );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AskPriceRecord> getAskpriceRequestList(SearchRequest request) {
		List<AskPriceRecord> list = new ArrayList<>();
		String sql = getSQLBySearchRequest(request, false);
		list = (List<AskPriceRecord>) queryListEntity(sql, null, AskPriceRecord.class);
		
		return list;
	}
	
	
	
	private String getSQLBySearchRequest(SearchRequest request, Boolean forCount) 
	{
		String sql = "SELECT \r\n" + 
				"    r.id, \r\n" + 
				"    r.name, \r\n" + 
				"    r.phone, \r\n" + 
				"    r.province, \r\n" + 
				"    r.city, \r\n" + 
				"    r.brand, \r\n" + 
				"    d.dealerShortName as dealer, \r\n" + 
				"    s.cs_show_name as serialName, \r\n" + 
				"    concat(i.car_name, ' ', i.car_year) as carName, \r\n" + 
				"    r.appsku, \r\n" + 
				"    r.channel, \r\n" + 
				"    date_format(r.requestTime, '%Y-%m-%d %H:%i:%s') as requestTime, \r\n" + 
				"    r.zt, \r\n" + 
				"    r.pagetype \r\n" + 
				"FROM \r\n" + 
				"    ask_price_request r \r\n" + 
				"        left join \r\n" + 
				"    car_dealer d ON r.dealerId = d.dealerId \r\n" + 
				"        left join \r\n" + 
				"    car_serial s ON s.cs_id = r.serialId \r\n" + 
				"        left join\r\n" + 
				"    car_info i ON i.car_id = r.carId \r\n" + 
				"where 1=1 \r\n";
		
		if (request.getAppsku() != null && !request.getAppsku().equals("")) 
		{
			sql = sql + " AND r.appsku = '" + request.getAppsku() + "'";
		}
		
		if (request.getStart_time() != null && !request.getStart_time().equals("") && request.getEnd_time() != null && !request.getEnd_time().equals("")) 
		{
			sql = sql + " AND DATE(r.requestTime) BETWEEN '" + request.getStart_time() + "'" +
		                " AND '" + request.getEnd_time() + "'";
		}
		
		if (request.getBrand() != null && !request.getBrand().equals("")) 
		{
			sql = sql + " AND r.brand = '" + request.getBrand() + "'";
		}
		
		if (request.getProvince() != null && !request.getProvince().equals("")) 
		{
			sql = sql + " AND r.province = '" + request.getProvince() + "'";
		}
		
		if (request.getCity() != null && !request.getCity().equals("")) 
		{
			sql = sql + " AND r.city = '" + request.getCity() + "'";
		}
		
		if (request.getChannel() != null && !request.getChannel().equals("")) 
		{
			sql = sql + " AND r.channel = '" + request.getChannel() + "'";
		}
		
		if (request.getZt() != null && !request.getZt().equals("")) 
		{
			sql = sql + " AND r.zt = '" + request.getZt() + "'";
		}
		
		sql = sql + " order by requestTime desc";
		
		if (forCount) 
		{
			return "select count(*) " + sql.substring(sql.indexOf("FROM"));
		}
		
		return sql; 
	}

	@Override
	public Integer getCountOfRequestPhone(SearchRequest request) {
		String sql = getSQLBySearchRequest(request, true);
		sql = sql.replace("select count(*)", "select count(distinct(phone))");
		return getCountBy(sql, null);
	}

}
