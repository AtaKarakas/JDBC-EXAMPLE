package com.proje.service.impl;

import java.util.List;

import com.proje.model.Category;
import com.proje.repository.CategoryRepo;
import com.proje.repository.impl.CategoryRepoImpl;
import com.proje.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo = new CategoryRepoImpl();
	
	@Override
	public Category findCategoryById(int id) {
		return categoryRepo.findCategoryById(id);
	}

	@Override
	public List<Category> findCategories() {
		return categoryRepo.findCategories();
	}

	
	
}
