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
@Table(name = "ask_price_request")
public class AskRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "brand")
	private String brand;

	@Column(name = "serialId")
	private Long serialId;
	
//	@OneToOne
//	@JoinColumn(name = "cs_id")
//	private CarSerial serial;

	@Column(name = "carId")
	private Long carId;
	
//	@OneToOne
//	@JoinColumn(name = "car_id")
//	private CarInfo car;

	@Column(name = "dealerId")
	private Long dealerId;
	
//	@OneToOne
//	@JoinColumn(name = "dealerId")
//	private CarDealer dealer;

	@Column(name = "appsku")
	private String appsku;

	@Column(name = "channel")
	private String channel;

	@Column(name = "requestTime")
	private Date requestTime;

	@Column(name = "zt")
	private String zt;

	@Column(name = "pagetype")
	private String pagetype;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAppsku() {
		return appsku;
	}

	public void setAppsku(String appsku) {
		this.appsku = appsku;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getPagetype() {
		return pagetype;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	

	public Date getRequestTime() {
		return requestTime;
	}

//	public CarSerial getSerial() {
//		return serial;
//	}
//
//	public CarInfo getCar() {
//		return car;
//	}
//
//	public CarDealer getDealer() {
//		return dealer;
//	}
//
//	public void setSerial(CarSerial serial) {
//		this.serial = serial;
//	}
//
//	public void setCar(CarInfo car) {
//		this.car = car;
//	}
//
//	public void setDealer(CarDealer dealer) {
//		this.dealer = dealer;
//	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

}
