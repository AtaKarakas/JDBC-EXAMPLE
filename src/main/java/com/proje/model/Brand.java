package com.proje.model;

public class Brand {

	private int brandId;
	
	private String brand_name;
	
	public Brand() {
	
		
	}

	public Brand(int brandId, String brand_name) {
		this.brandId = brandId;
		this.brand_name = brand_name;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brand_name=" + brand_name + "]";
	}
	
	
	
	
}
