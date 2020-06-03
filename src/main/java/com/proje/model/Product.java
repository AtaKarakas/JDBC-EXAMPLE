package com.proje.model;

import java.util.Date;

public class Product {

	private int productId;
	
	private String productName;
	
	private double unitPrice;
	
	private int avaible;
	
	private Date addTime;
	
	private Date updateTime;
	
	private Category category;
	
	private Brand brand;
	
	public Product() {
	
	}

	public Product(int productId, String productName, double unitPrice, int avaible, Date addTime, Date updateTime,
			Category category, Brand brand) {
	
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.avaible = avaible;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.category = category;
		this.brand = brand;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAvaible() {
		return avaible;
	}

	public void setAvaible(int avaible) {
		this.avaible = avaible;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Category getcategory() {
		return category;
	}

	public void setcategory(Category category) {
		this.category = category;
	}

	public Brand getbrand() {
		return brand;
	}

	public void setbrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", unitPrice=" + unitPrice
				+ ", avaible=" + avaible + ", addTime=" + addTime + ", updateTime=" + updateTime + ", category="
				+ category + ", brand=" + brand + "]";
	}
	
	
	
	
}
