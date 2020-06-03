package com.proje.model.queries;

public class ProductQueries {

	public static final String saveProductQuery = "INSERT INTO product (productId, productName, unitPrice, avaible, addTime, updateTime, category, brand) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String updateProductQuery = "UPDATE product SET productName = ?, unitPrice = ?, avaible = ?, updateTime = ?, category = ?, brand = ? WHERE productId = ?";
	
	public static final String deleteUserProductQuery = "DELETE FROM user_product WHERE productId = ?";
	public static final String deleteProductQuery = "DELETE FROM product WHERE productId = ?";
	
	public static final String findProductByIdQuery = "SELECT * FROM product p LEFT JOIN category c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId) WHERE productId = ?";
	
	public static final String findProductQuery = "SELECT * FROM product p LEFT JOIN category c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId)";
}
