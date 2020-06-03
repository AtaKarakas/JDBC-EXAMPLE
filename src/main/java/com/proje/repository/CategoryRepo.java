package com.proje.repository;

import java.util.List;

import com.proje.model.Category;

public interface CategoryRepo {
	
	Category findCategoryById(int id);
	
	List<Category> findCategories();

}
