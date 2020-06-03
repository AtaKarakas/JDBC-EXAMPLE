package com.proje.service.impl;

import java.util.List;

import com.proje.model.Brand;
import com.proje.repository.BrandRepo;
import com.proje.repository.impl.BrandRepoImpl;
import com.proje.service.BrandService;

public class BrandServiceImpl implements BrandService {

	private BrandRepo brandRepo = new BrandRepoImpl();
	
	@Override
	public Brand saveBrand(Brand brand) {
		return brandRepo.saveBrand(brand);
	}

	@Override
	public Brand findBrandById(int id) {
		return brandRepo.findBrandById(id);
	}

	@Override
	public List<Brand> findBrands() {
		return brandRepo.findBrands();
	}

	
	
}
