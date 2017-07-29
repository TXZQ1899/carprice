package com.askprice.carprice.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.askprice.carprice.dto.AskPrice;
import com.askprice.carprice.entity.CarCityDealer;

public interface CarCityDealerDao extends CrudRepository<CarCityDealer, Long> {

	@Query("SELECT d from CarCityDealer d WHERE d.cityId = :cityId and d.carId = :carId")
	List<CarCityDealer> getDealerByCityIdAndCarId(@Param("cityId") String cityId, @Param("carId") Long carId);
	
	@Query("SELECT d from CarCityDealer d WHERE d.cityId = :cityId and d.carId = :carId")
	Page<AskPrice> searchAskPriceRequest(Pageable pageable);

}
