package com.askprice.carprice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car_city_dealer")
public class CarCityDealer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "cityId")
	private String cityId;
	
	@Column(name = "carId")
	@OneToOne
	@JoinColumn(name = "car_id")
	private CarInfo carId;
	
	@Column(name = "serialId")
	private Long serialId;
	
//	@Column(name = "dealerId")
	@OneToOne
	@JoinColumn(name = "dealerId")
	private CarDealer dealer;
	
	@Column(name = "updateTime")
	private Date updateTime;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	public void initCityDealer(String cityId, Long carId, Long dealerId) 
	{
		this.cityId = cityId;
		this.carId = carId;
		this.dealerId = dealerId;
		this.updateTime = new Date();
	}

}
