package com.askprice.carprice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_dealer")
public class CarDealer implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "bizMode")
	private String bizMode;
	
	@Column(name = "dealerId")
	private Long dealerId;
	
	@Column(name = "dealerShortName")
	private String dealerShortName;
	
	@Column(name = "minPrice")
	private Double minPrice;
	
	@Column(name = "maxPrice")
	private Double maxPrice;
	
	@Column(name = "tel400")
	private String tel400;
	
	@Column(name = "saleRange")
	private String saleRange;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "newsTitle")
	private String newsTitle;
	
	@Column(name = "newsUrl")
	private String newsUrl;
	
	@Column(name = "newsRemainingDays")
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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
