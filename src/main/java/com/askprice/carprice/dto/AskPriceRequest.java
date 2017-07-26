package com.askprice.carprice.dto;

public class AskPriceRequest {
	
	private String name;
	
	private String phone;
	
	private String reqId;
	
	private String smscode;
	
	private String province;
	
	private String city;
	
	private String brand;
	
	private Long serialId;
	
	private Long carId;
	
	private Long[] dealers;
	
	private String appsku;
	
	private String channel;
	
	private String zt;
	
	private String pagetype;

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

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
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

	public Long[] getDealers() {
		return dealers;
	}

	public void setDealers(Long[] dealers) {
		this.dealers = dealers;
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

}
