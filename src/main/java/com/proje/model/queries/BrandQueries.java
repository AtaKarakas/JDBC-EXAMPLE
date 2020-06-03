package com.proje.model.queries;

public class BrandQueries {

	public static final String saveBrandQuery ="INSERT INTO brand (brandId, brandName) VALUES (?, ?)";
	
	public static final String updateBrandQuery ="UPDATE brand SET brandName = ?";
	
	public static final String deleteBrandQuery ="DELETE FROM brand WHERE brandId= ?";
	
	public static final String findBrandByIdQuery ="SELECT * FROM brand WHERE brandId = ?";
	
	public static final String findBrandsQuery = "SELECT * FROM brand ";
	
	
}
