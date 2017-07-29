package com.askprice.carprice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_serial")
public class CarSerial implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cs_id")
	private Long serialId;
	
	@Column(name = "bs_id")
	private Long bsId;
	
	@Column(name = "cb_id")
	private Long brandId;
	
	@Column(name = "cs_show_name")
	private String carName;
	
	@Column(name = "cb_name")
	private String carBrand;
	
	@Column(name = "mb_name")
	private String mbName;
	
	@Column(name = "logo")
	private String logo;
	
	@Column(name = "cs_pic")
	private String carPic;
	
	@Column(name = "country")
	private String county;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "displacement")
	private String displacement;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "max_price")
	private Double maxPrice;
	
	@Column(name = "min_price")
	private Double minPrice;
	
	@Column(name = "create_by")
    private String createBy;
    
    @Column(name = "update_by")
    private String updateBy;
    
    @Column(name = "create_time")
    private Date createTime;
    
    @Column(name = "update_time")
    private Date updateTime;
    
    @Column(name = "is_deleted")
    private Short isDeleted;
    
    @Column(name = "dealer_update_time")
    private Date dealerUpdateTime;

    @Column(name = "CsPriceRange")
    private String csPriceRange;

	public Long getSerialId() {
		return serialId;
	}

	public Long getBsId() {
		return bsId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public String getCarName() {
		return carName;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public String getMbName() {
		return mbName;
	}

	public String getLogo() {
		return logo;
	}

	public String getCarPic() {
		return carPic;
	}

	public String getCounty() {
		return county;
	}

	public String getType() {
		return type;
	}

	public String getDisplacement() {
		return displacement;
	}

	public String getLevel() {
		return level;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public String getCreateBy() {
		return createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Short getIsDeleted() {
		return isDeleted;
	}

	public Date getDealerUpdateTime() {
		return dealerUpdateTime;
	}

	public String getCsPriceRange() {
		return csPriceRange;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public void setMbName(String mbName) {
		this.mbName = mbName;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setCarPic(String carPic) {
		this.carPic = carPic;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setDealerUpdateTime(Date dealerUpdateTime) {
		this.dealerUpdateTime = dealerUpdateTime;
	}

	public void setCsPriceRange(String csPriceRange) {
		this.csPriceRange = csPriceRange;
	}
    
}
