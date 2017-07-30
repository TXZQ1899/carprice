package com.askprice.carprice.dto;

import java.math.BigInteger;
import java.util.Date;

import com.askprice.carprice.common.util.excel.ExcelResources;

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

	@ExcelResources(title="编号",order=1)
	public BigInteger getId() {
		return id;
	}

	@ExcelResources(title="姓名",order=2)
	public String getName() {
		return name;
	}

	@ExcelResources(title="手机",order=3)
	public String getPhone() {
		return phone;
	}

	@ExcelResources(title="省份",order=4)
	public String getProvince() {
		return province;
	}

	@ExcelResources(title="城市",order=5)
	public String getCity() {
		return city;
	}

	@ExcelResources(title="品牌",order=6)
	public String getBrand() {
		return brand;
	}

	@ExcelResources(title="车系",order=7)
	public String getSerialName() {
		return serialName;
	}

	@ExcelResources(title="车型",order=8)
	public String getCarName() {
		return carName;
	}

	@ExcelResources(title="经销商",order=10)
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

	@ExcelResources(title="咨询时间",order=9)
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

	public AskPriceRecord(BigInteger id, String name, String phone, String province, String city, String brand,
			String serialName, String carName, String dealer, String requestTime) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.brand = brand;
		this.serialName = serialName;
		this.carName = carName;
		this.dealer = dealer;
		this.requestTime = requestTime;
	}
	
	public AskPriceRecord() {}

	

}
