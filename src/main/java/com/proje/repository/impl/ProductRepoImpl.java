package com.proje.repository.impl;

import java.security.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.queries.ProductQueries;
import com.proje.repository.ProductRepo;

public class ProductRepoImpl implements ProductRepo {

	private final Logger logger = LogManager.getLogger();
	
	private Connection connection ;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@Override
	public Product saveProduct(Product product) {
		
		connection = DBConnection.getConnection();
		
		//"INSERT INTO product (productId, productName, unitPrice, avaible, addTime, updateTime, categoryId, brandId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.saveProductQuery);
			
			
			
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(localDateTime));
			preparedStatement.setTimestamp(6, null);
			preparedStatement.setInt(7, product.getcategory().getCategoryId());
			preparedStatement.setInt(8, product.getbrand().getBrandId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Product kaydedilirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return product;
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		
		connection = DBConnection.getConnection();
		
		//"INSERT INTO product (productId, productName, unitPrice, avaible, addTime, updateTime, categoryId, brandId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.saveProductQuery);
			
			for (Product product : products) {
			
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(localDateTime));
			preparedStatement.setTimestamp(6, null);
			preparedStatement.setInt(7, product.getcategory().getCategoryId());
			preparedStatement.setInt(8, product.getbrand().getBrandId());
			preparedStatement.addBatch();
			
			}
			
			preparedStatement.executeBatch();
			
		} catch (SQLException e) {
			logger.warn("Product listesi kaydedilirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return true;
	}

	@Override
	public Product updateProduct(Product product) {
		// UPDATE product SET productName = ?, unitPrice = ?, avaible = ?, updateTime = ?, category = ?, brand = ? WHERE productId = ?";
		

		connection = DBConnection.getConnection();

		LocalDateTime localDateTime = LocalDateTime.now();
	
		try {
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.saveProductQuery);
						
			
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getUnitPrice());
			preparedStatement.setInt(3, product.getAvaible());
			preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(localDateTime));
			preparedStatement.setInt(5, product.getcategory().getCategoryId());
			preparedStatement.setInt(6, product.getbrand().getBrandId());
			preparedStatement.setInt(7, product.getProductId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Ürün güncelleirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		
		return product;
	}

	@Override
	public boolean removeProduct(int id) {

		connection = DBConnection.getConnection();
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.deleteUserProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.deleteProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Product Silinirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		
		return false;
	}

	@Override
	public Product findProductById(int id) {
		
		//"SELECT * FROM product p LEFT JOIN categroy c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId) WHERE productId = ?";
		connection = DBConnection.getConnection();
		
		Product product = null;
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.findProductByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				double unitPrice = resultSet.getDouble("unitPrice");
				int avaible = resultSet.getInt("avaible");
				Date addTime = resultSet.getDate("addTime");
				Date updateTime = resultSet.getDate("updateTime");
				
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");
				
				int brandId = resultSet.getInt("brandId");
				String brand_name = resultSet.getString("brandName");
				
				Category category = new Category(categoryId, categoryName);
				Brand brand = new Brand(brandId, brand_name);
				
				product = new Product(productId, productName, unitPrice, avaible, addTime, updateTime, category, brand);
				
				
			}	
			
		} catch (SQLException e) {
			
			logger.warn(id + " id'li ürün aranırken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return product;
	}

	@Override
	public List<Product> findProducts() {

		connection = DBConnection.getConnection();
		
		List<Product> products = new ArrayList<Product>();
		
		//" SELECT * FROM product p LEFT JOIN category c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId)"
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(ProductQueries.findProductQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getString("productName");
				double unitPrice = resultSet.getDouble("unitPrice");
				int avaible = resultSet.getInt("avaible");
				Date addTime = resultSet.getDate("addTime");
				Date updateTime = resultSet.getDate("updateTime");
				
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");
				
				int brandId = resultSet.getInt("brandId");
				String brand_name = resultSet.getString("brandName");
				
				Category category = new Category(categoryId, categoryName);
				Brand brand = new Brand(brandId, brand_name);
				
				Product product = new Product(productId, productName, unitPrice, avaible, addTime, updateTime, category, brand);
				products.add(product);
				
			}
			
		} catch (SQLException e) {
			logger.warn("Urunler listelenirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		
	return products;
	}

}
