package com.proje.service.impl;

import java.util.List;

import com.proje.model.Product;
import com.proje.repository.ProductRepo;
import com.proje.repository.impl.ProductRepoImpl;
import com.proje.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductRepo productRepo = new ProductRepoImpl();
	
	@Override
	public Product saveProduct(Product product) {
		return productRepo.saveProduct(product);
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		return productRepo.saveBatchProduct(products);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepo.updateProduct(product);
	}

	@Override
	public boolean removeProduct(int id) {
		return productRepo.removeProduct(id);
	}

	@Override
	public Product findProductById(int id) {
		return productRepo.findProductById(id);
	}

	@Override
	public List<Product> findProducts() {
		return productRepo.findProducts();
	}

}
