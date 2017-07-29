package com.askprice.carprice.dto;

import java.math.BigInteger;
import java.util.Date;

public class AskPriceRecord {
	
	private BigInteger id;
	
	private String name;
	
	private String phone;
	
	private String province;
	
	private String city;
	
	private String brand;
	
	private String serialName;
	
	private String carName;
	
	private String dealer;
	
	private String appsku;
	
	private String channel;
	
	private String zt;
	
	private String pagetype;

	private String requestTime;

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getBrand() {
		return brand;
	}

	public String getSerialName() {
		return serialName;
	}

	public String getCarName() {
		return carName;
	}

	public String getDealer() {
		return dealer;
	}

	public String getAppsku() {
		return appsku;
	}

	public String getChannel() {
		return channel;
	}

	public String getZt() {
		return zt;
	}

	public String getPagetype() {
		return pagetype;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public void setAppsku(String appsku) {
		this.appsku = appsku;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	

}
