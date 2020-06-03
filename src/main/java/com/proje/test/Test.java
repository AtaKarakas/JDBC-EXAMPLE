package com.proje.test;

import java.util.List;

import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.service.BrandService;
import com.proje.service.CategoryService;
import com.proje.service.ProductService;
import com.proje.service.UserService;
import com.proje.service.impl.BrandServiceImpl;
import com.proje.service.impl.CategoryServiceImpl;
import com.proje.service.impl.ProductServiceImpl;
import com.proje.service.impl.UserServiceImpl;

public class Test {

	public static void main(String[] args) {
		
		CategoryService categoryService = new CategoryServiceImpl();
		
		BrandService brandService = new BrandServiceImpl();
		
		ProductService productService = new ProductServiceImpl();
		
		UserService userService = new UserServiceImpl();
		
//		List<Category> categories = categoryService.findCategories();
//		for (Category category : categories) {
//			System.out.println(category.getCategoryId()+ "--"+ category.getCategoryName());
//		}
		
		Category category = categoryService.findCategoryById(1);
		System.out.println(category.getCategoryName());
				
	}
	
}
