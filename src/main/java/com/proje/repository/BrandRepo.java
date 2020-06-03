package com.proje.repository;

import java.util.List;

import com.proje.model.Brand;

public interface BrandRepo {

	Brand saveBrand (Brand brand);
	
	Brand findBrandById(int id);
	
	List<Brand> findBrands();
	
}
