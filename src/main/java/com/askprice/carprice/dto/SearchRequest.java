package com.askprice.carprice.dto;

public class SearchRequest {
	private String brand;
	
	private String province;
	
	private String city;
	
	private String appsku;
	
	private String channel;
	
	private String zt;
	
	private String key;
	
	private String start_time;
	
	private String end_time;
	
	private String page_type;
	
	private Integer page_size;
	
	private Integer page_no;
	
	public SearchRequest(){}

	public SearchRequest(String brand, String province, String city, String appsku, String channel, String zt,
			String start_time, String end_time, String page_type, Integer page_size, Integer page_no) {
		super();
		this.brand = brand;
		this.province = province;
		this.city = city;
		this.appsku = appsku;
		this.channel = channel;
		this.zt = zt;
		this.start_time = start_time;
		this.end_time = end_time;
		this.page_type = page_type;
		this.page_size = page_size;
		this.page_no = page_no;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

}
