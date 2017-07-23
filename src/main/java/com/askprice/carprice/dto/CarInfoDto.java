package com.askprice.carprice.dto;

public class CarInfoDto {
	
	private Long carId;
	
	private Long serialId;
	
	private String carName;
	
	private String carYear;
	
	private String showName;
	
	private String brandName;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
//	select 
//    i.car_id as carId,
//    i.cs_id as serialId,
//    i.car_name as carName,
//    i.car_year as carYear,
//    s.cs_show_name as showName,
//    s.mb_name as brandName
//from
//    car_info i
//        LEFT JOIN
//    car_serial s ON i.cs_id = s.cs_id
//where
//    s.cs_id = 4477
//order by car_year desc; 

}
