package com.askprice.carprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDealer {

	private String bizMode;
	
	private Long dealerId;
	
	private String dealerShortName;
	
	private Double minPrice;
	
	private Double maxPrice;
	
	private String tel400;
	
	private String saleRange;
	
	private String address;
	
	private Double longitude;
	
	private Double latitude;
	
	private String newsTitle;
	
	private String newsUrl;
	
	private Integer newsRemainingDays;

	public String getBizMode() {
		return bizMode;
	}

	public void setBizMode(String bizMode) {
		this.bizMode = bizMode;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerShortName() {
		return dealerShortName;
	}

	public void setDealerShortName(String dealerShortName) {
		this.dealerShortName = dealerShortName;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getTel400() {
		return tel400;
	}

	public void setTel400(String tel400) {
		this.tel400 = tel400;
	}

	public String getSaleRange() {
		return saleRange;
	}

	public void setSaleRange(String saleRange) {
		this.saleRange = saleRange;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public Integer getNewsRemainingDays() {
		return newsRemainingDays;
	}

	public void setNewsRemainingDays(Integer newsRemainingDays) {
		this.newsRemainingDays = newsRemainingDays;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "CarDealer [bizMode=" + bizMode + ", dealerId=" + dealerId + ", dealerShortName=" + dealerShortName
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", tel400=" + tel400 + ", saleRange="
				+ saleRange + ", address=" + address + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", newsTitle=" + newsTitle + ", newsUrl=" + newsUrl + ", newsRemainingDays=" + newsRemainingDays
				+ "]";
	}


	
}
